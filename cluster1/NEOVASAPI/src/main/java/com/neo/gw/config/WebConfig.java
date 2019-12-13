package com.neo.gw.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo.gw.handler.BasicChannelHandler;
import com.neo.gw.model.*;
import com.neo.gw.util.Constant;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.grid.GridBucketState;
import io.github.bucket4j.grid.ProxyManager;
import io.github.bucket4j.grid.coherence.Coherence;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/** Class config bean */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  // cache dung chung giua cac cluster
  @Bean(name = "bucket4jCache")
  @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
  public NamedCache cache() {
    return CacheFactory.getCache(Constant.CACHE_NAME);
  }

  // config the proxy bucket
  @Bean("buckets")
  @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
  public ProxyManager getBuckets() {
    return Bucket4j.extension(Coherence.class).proxyManagerForCache(cache());
  }

  /*  create bean apiSqlConfig use to serviceUtility, get url of soap*/
  @Bean("apiSqlConfig")
  @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
  public PropertiesConfiguration getApiSqlProperties() throws ConfigurationException {

    PropertiesConfiguration prop = new PropertiesConfiguration();
    prop.setDelimiterParsingDisabled(true);
    prop.setEncoding("UTF8");
    prop.setPath("apisql.properties");
    prop.load();
    prop.setReloadingStrategy(new FileChangedReloadingStrategy());

    return prop;
  }

  // init hashMap data User from database
  @Bean("hashMapData")
  @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
  public Map<String, Map<String, BasicChannelHandler>> getConcurrentHashMap() {

    Map<String, Map<String, BasicChannelHandler>> test = new ConcurrentHashMap<>();

    Map<String, BasicChannelHandler> handlerMap = new LinkedHashMap<>();

    handlerMap.put(Constant.USERNAME_KEY, new Username("admin"));
    handlerMap.put(Constant.PASSWORD_KEY, new Password("e10adc3949ba59abbe56e057f20f883e"));
    handlerMap.put(Constant.IP_KEY, new Ip("10.252.10.35"));
    handlerMap.put(Constant.TPS_KEY, new Tps(10));
    handlerMap.put(Constant.SERVICE_CODE_KEY, new Services("s1"));
    handlerMap.put(Constant.PKG_CODE_KEY, new Pkg("p1"));

    test.put("admin", handlerMap);
    return test;
  }

  @Bean(name = "objectMapper")
  @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

}
