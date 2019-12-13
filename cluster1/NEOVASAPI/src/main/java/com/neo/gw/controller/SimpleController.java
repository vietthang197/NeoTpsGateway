package com.neo.gw.controller;

import com.neo.gw.controller.vm.DefaultRequestVM;
import com.neo.gw.handler.ChannelHandler;
import com.neo.gw.services.PipelineChannel;
import com.neo.gw.util.Constant;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class SimpleController {

    // logger
    private Logger logger = LoggerFactory.getLogger(SimpleController.class);

    @Autowired
    private PipelineChannel<ChannelHandler> pipelineChannel;

    @PostMapping("/check-tps")
    public String checkTps(@RequestBody DefaultRequestVM defaultRequestVM, ServletRequest request, ServletResponse response) {
        // set ip address for request object
        defaultRequestVM.getAuthen().put(Constant.IP_KEY, request.getRemoteAddr());

        return pipelineChannel.execute(defaultRequestVM, (HttpServletRequest) request, (HttpServletResponse) response); // khong the tao ra  ChannelHandler[] obj
    }

    @GetMapping("/cache-size")
    public Integer cacheSize() {
        NamedCache namedCache = CacheFactory.getCache(Constant.CACHE_NAME);

        return namedCache.size();
    }
}
