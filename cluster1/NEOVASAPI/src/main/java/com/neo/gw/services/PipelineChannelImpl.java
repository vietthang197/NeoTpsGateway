package com.neo.gw.services;

import com.neo.gw.controller.vm.DefaultRequestVM;
import com.neo.gw.exception.RequestInvalidException;
import com.neo.gw.handler.BasicChannelHandler;
import com.neo.gw.handler.ChannelHandler;
import com.neo.gw.model.Tps;
import com.neo.gw.util.Constant;
import com.tangosol.net.NamedCache;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import io.github.bucket4j.grid.ProxyManager;
import io.github.bucket4j.grid.RecoveryStrategy;
import io.github.bucket4j.grid.coherence.Coherence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Map;

@Service
public class PipelineChannelImpl implements PipelineChannel<ChannelHandler> {

  private Logger logger = LoggerFactory.getLogger(PipelineChannelImpl.class);

  @Autowired
  @Qualifier("hashMapData")
  private Map<String, Map<String, BasicChannelHandler>> dataUser;

  @Autowired
  @Qualifier("bucket4jCache")
  private NamedCache cache;

  @Autowired
  private ProxyManager buckets;

  @Override
  public String execute(DefaultRequestVM defaultRequestVM, HttpServletRequest request, HttpServletResponse response) {
    StringBuilder key = new StringBuilder();

    String username = defaultRequestVM.getAuthen().get(Constant.USERNAME_KEY);

    if (!dataUser.containsKey(username)) {
      throw new RequestInvalidException("Tên đăng nhập không tồn tại");
    }

    Map<String, BasicChannelHandler> data = dataUser.get(username);

    for(Map.Entry<String, BasicChannelHandler> item : data.entrySet()) {
      item.getValue().before(defaultRequestVM, request, response);
      key.append(item.getValue().execute(defaultRequestVM, request, response));
      item.getValue().after(defaultRequestVM, request, response);
    }

    // nhả ra key sau khi check xong
    long tps = ((Tps)data.get(Constant.TPS_KEY)).getTps();

    Bucket bucket = Bucket4j.extension(Coherence.class).builder()
            .addLimit(Bandwidth.classic(tps, Refill.intervally(tps, Duration.ofMinutes(1))))
            .build(cache, key, RecoveryStrategy.RECONSTRUCT);

    if (bucket.tryConsume(1)) {
      return "allow";
    } else {
      return "limit the request";
    }
  }
}
