/**
 * Created By Eng. Pius Obonyo
 * Date: 10/6/24
 * Time: 12:54 PM
*/

package com.dfcu.HR_Management_System.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    private final AtomicInteger totalRequests = new AtomicInteger(0);
    private final AtomicInteger successfulRequests = new AtomicInteger(0);
    private final AtomicInteger failedRequests = new AtomicInteger(0);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Log the request URI and HTTP method
        log.info("Request URI: {}", request.getRequestURI());
        log.info("HTTP Method: {}", request.getMethod());

        // Log headers
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            log.info("Header: {} = {}", headerName, request.getHeader(headerName));
        }

        // Log request parameters (if any)
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            log.info("Parameter: {} = {}", paramName, request.getParameter(paramName));
        }

        // Increment total requests
        totalRequests.incrementAndGet();

        // Continue the filter chain
        filterChain.doFilter(request, response);

        // Log the response status code
        log.info("Response Status: {}", response.getStatus());

        // Track success and failure based on status code
        if (response.getStatus() >= 200 && response.getStatus() < 300) {
            successfulRequests.incrementAndGet();
        } else if (response.getStatus() >= 400 && response.getStatus() < 600) {
            failedRequests.incrementAndGet();
        }
    }

    // Getter methods for the metrics
    public int getTotalRequests() {
        return totalRequests.get();
    }

    public int getSuccessfulRequests() {
        return successfulRequests.get();
    }

    public int getFailedRequests() {
        return failedRequests.get();
    }
}


