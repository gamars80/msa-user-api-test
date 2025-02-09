package com.example.msauserapitest.logging;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MDCLoggingFilter implements Filter {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Environment environment;

    private static final List<Integer> CLIENT_ERROR_RESPONSE_LIST = List.of(400, 499);
    private static final List<Integer> SERVER_ERROR_RESPONSE_LIST = List.of(500, 599);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper(
                (HttpServletRequest) request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper(
                (HttpServletResponse) response);
        chain.doFilter(httpServletRequest, httpServletResponse);

        String requestBody = new String(httpServletRequest.getContentAsByteArray());
        if (requestBody.isBlank()) {
            requestBody = StreamUtils.copyToString(httpServletRequest.getInputStream(),
                    StandardCharsets.UTF_8);
        }

        int httpStatus = httpServletResponse.getStatus();
        String responseBody = new String(httpServletResponse.getContentAsByteArray());
        MDC.put("application", "gacha-api");
        MDC.put("ip_address", InetAddress.getLocalHost().getHostAddress());
        MDC.put("environment", getCurrentEnvironment());
        MDC.put("request_method", ((HttpServletRequest) request).getMethod());
        MDC.put("request_api", ((HttpServletRequest) request).getRequestURI());
        MDC.put("http_status", String.valueOf(httpStatus));
        MDC.put("request_body", requestBody);
        MDC.put("response_body", responseBody);

        if (httpStatus >= CLIENT_ERROR_RESPONSE_LIST.get(0) && httpStatus <= CLIENT_ERROR_RESPONSE_LIST.get(1)) {
            MDC.put("error_type", "CLIENT_ERROR");
            logger.error("request error:{}", request);
        }

        if (httpStatus >= SERVER_ERROR_RESPONSE_LIST.get(0) && httpStatus <= SERVER_ERROR_RESPONSE_LIST.get(1)) {
            MDC.put("error_type", "SERVER_ERROR");
            logger.error("request error:{}", request);
        }

        MDC.clear();
        httpServletResponse.copyBodyToResponse();
    }

    private String getCurrentEnvironment() {
        if (environment.getActiveProfiles().length != 0) {
            return (environment.getActiveProfiles()[0]);
        }

        return environment.getDefaultProfiles()[0];
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
