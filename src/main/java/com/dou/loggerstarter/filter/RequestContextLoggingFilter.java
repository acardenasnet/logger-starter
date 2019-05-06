package com.dou.loggerstarter.filter;

import com.dou.loggerstarter.util.Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestContextLoggingFilter implements Filter {


  private static final Logger LOGGER = LoggerFactory.getLogger(RequestContextLoggingFilter.class);

  private String requestHeaderId = "X-REQUEST-ID";
  private String logIdentifier = "requestId";

  public RequestContextLoggingFilter(String requestHeaderId, String logIdentifier) {
    super();
    if (StringUtils.isNotEmpty(requestHeaderId)) {
      this.requestHeaderId = requestHeaderId;
    }

    if (StringUtils.isNotEmpty(logIdentifier)) {
      this.logIdentifier = logIdentifier;
    }
  }

  @Override
  public void destroy() {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    try {

      if (!(request instanceof HttpServletRequest)) {
        throw new ServletException("RequestContextLoggingFilter just supports HTTP requests");
      }

      HttpServletRequest httpServletRequest = (HttpServletRequest) request;

      String requestUUID = httpServletRequest.getHeader(requestHeaderId);

      if (StringUtils.isEmpty(requestUUID)) {
        requestUUID = Util.createId();
        LOGGER.info("Got request {} without {} and assign new {}", httpServletRequest.getRequestURI(), requestHeaderId,
            requestUUID);

      }
      MDC.put(logIdentifier, requestUUID);

      HttpServletResponse httpServletResponse = (HttpServletResponse) response;
      httpServletResponse.addHeader(requestHeaderId, requestUUID);

      chain.doFilter(request, response);
    } finally {
      MDC.clear();
    }
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {

  }
}
