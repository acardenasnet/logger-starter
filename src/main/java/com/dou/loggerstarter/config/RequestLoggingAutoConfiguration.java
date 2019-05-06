package com.dou.loggerstarter.config;

import com.dou.loggerstarter.filter.RequestContextLoggingFilter;
import com.dou.loggerstarter.properties.RequestLoggingProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass(RequestContextLoggingFilter.class)
@EnableConfigurationProperties(RequestLoggingProperties.class)
public class RequestLoggingAutoConfiguration {

  @Autowired
  private RequestLoggingProperties requestLoggingProperties;

  @Bean
  @ConditionalOnMissingBean
  public RequestContextLoggingFilter requestContextLoggingFilter() {
    return new RequestContextLoggingFilter(requestLoggingProperties.getRequestHeaderId(),
        requestLoggingProperties.getLogIdentifier());
  }

}
