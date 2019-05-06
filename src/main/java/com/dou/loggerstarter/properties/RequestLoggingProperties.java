package com.dou.loggerstarter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "dou.requestlogging")
public class RequestLoggingProperties {

  /**
   * Name of the HTTP Header containing the request Id. defaults to "X-REQUEST-ID"
   */
  private String requestHeaderId;

  /**
   * Name of the logback variable containing the request Id value; defaults to  "requestId"
   */
  private String logIdentifier;

  public String getRequestHeaderId() {
    return requestHeaderId;
  }

  public void setRequestHeaderId(String requestHeaderId) {
    this.requestHeaderId = requestHeaderId;
  }

  public String getLogIdentifier() {
    return logIdentifier;
  }

  public void setLogIdentifier(String logIdentifier) {
    this.logIdentifier = logIdentifier;
  }
}
