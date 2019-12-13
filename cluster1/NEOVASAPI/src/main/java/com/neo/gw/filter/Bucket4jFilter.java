package com.neo.gw.filter;

import com.neo.gw.controller.vm.ConfigurationLazySupplier;
import com.neo.gw.controller.vm.DefaultRequestVM;
import com.neo.gw.services.LimitProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import java.io.IOException;


public class Bucket4jFilter {

  // config the logger
  private Logger logger = LoggerFactory.getLogger(Bucket4jFilter.class);

  // service to provide per user limits
  private LimitProviderService limitProvider;

  // cache for storing token buckets,  IP is key.
//  @Autowired
//  @Qualifier("bucketCache")
//  private javax.cache.Cache<String, GridBucketState> cache;

  //private ProxyManager<String> buckets;


  public void init(FilterConfig filterConfig) throws ServletException {
    logger.debug("init Filter");
    // init bucket registry
   // buckets = Bucket4j.extension(JCache.class).proxyManagerForCache(cache);
  }


  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    filterChain.doFilter(servletRequest, servletResponse);

    /*HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
    HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

    try {
      InputStreamReader reader = new InputStreamReader(httpRequest.getInputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }

    // prepare configuration supplier which will be called(on first interaction with proxy) if
    // bucket was not saved yet previously.
    ConfigurationLazySupplier config = getConfigSupplierForUser(httpRequest, httpResponse);

    if (config == null) {
      httpResponse.setContentType("text/plain");
      httpResponse.setStatus(400);
      try {
        httpResponse.getWriter().append("Invalid request body");
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      // acquire cheap proxy to bucket
      Bucket bucket = buckets.getProxy(config.getKey(), config.getConfig());

      logger.debug("bucket getProxy: {}", bucket);

      // tryConsume returns false immediately if no tokens available with the bucket
      if (bucket.tryConsume(1)) {
        // the limit is not exceeded
        try {
          filterChain.doFilter(servletRequest, servletResponse);
        } catch (IOException e) {
          e.printStackTrace();
        } catch (ServletException e) {
          e.printStackTrace();
        }
      } else {
        // limit is exceeded
        httpResponse.setContentType("text/plain");
        httpResponse.setStatus(429);
        try {
          httpResponse.getWriter().append("Too many requests");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }*/
  }

//  private ConfigurationLazySupplier getConfigSupplierForUser(
//      HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
//    return limitProvider.getLimitForRequest(httpRequest, httpResponse);
//  }
}
