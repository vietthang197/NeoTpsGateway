package com.neo.gw.services;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.net.URLEncoder;

@Service
public class ServiceUtility {

  /** đọc file apisql.properties */
  @Autowired
  @Qualifier("apiSqlConfig")
  private PropertiesConfiguration prop;

  private static Logger log = LoggerFactory.getLogger(ServiceUtility.class);

  /**
   * @param ServiceType
   * @param ServiceProvider
   * @param Service
   * @param Parameters
   * @return String
   */
  public String callService(
      String ServiceType, String ServiceProvider, String Service, String... Parameters) {

    log.debug(
        "ServiceUtility.getService[ServiceType:{}, ServiceProvider:{}, Service:{}, Parameters: Size = {}, Value = {}]",
        ServiceType,
        ServiceProvider,
        Service,
        Parameters.length,
        Parameters);

    int paramSize = Parameters.length;

    StringBuilder url = new StringBuilder();

    url.append(
        prop.getString(
                new StringBuilder(String.valueOf(ServiceProvider))
                    .append(".service_url")
                    .toString())).append(ServiceType);

    int timeout = prop.getInt(ServiceProvider + ".service_timeout");

    DefaultHttpClient httpclient = new DefaultHttpClient();

    try {
      HttpConnectionParams.setConnectionTimeout(httpclient.getParams(), 5000);
      HttpConnectionParams.setSoTimeout(httpclient.getParams(), timeout);
      url.append("?response=application/json&Service=")
          .append(Service)
          .append("&Provider=")
          .append(ServiceProvider)
          .append("&ParamSize=")
          .append(paramSize);

      for (int i = 0; i < paramSize; i++) {
        url.append("&P")
            .append((i + 1))
            .append("=")
            .append((Parameters[i] == null ? null : URLEncoder.encode(Parameters[i])));
      }

      HttpGet get = new HttpGet(url.toString());

      log.debug("URL CALL SOAP: {}", url);

      return IOUtils.toString(httpclient.execute(get).getEntity().getContent(), "UTF-8");
    } catch (Exception e) {
      e.printStackTrace();
      log.error("CALL SOAP FAIL: {}", e.getMessage());
    } finally {
      try {
        httpclient.getConnectionManager().shutdown();
      } catch (Exception ex) {
        log.error("HTTPCLIENT SHUTDOWN FAIL : {}", ex.getMessage());
      }
    }
    return null;
  }
}
