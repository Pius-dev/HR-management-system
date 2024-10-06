/**
 * Created By Eng. Pius Obonyo
 * Date: 10/6/24
 * Time: 12:53 PM
 */

package com.dfcu.HR_Management_System.service.Impl;

import com.dfcu.HR_Management_System.dto.MetricsDto;
import com.dfcu.HR_Management_System.filters.RequestLoggingFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MetricsService {

    private final RequestLoggingFilter requestLoggingFilter;

    public MetricsDto getApiMetrics() {
        return MetricsDto.builder()
                .totalRequests(requestLoggingFilter.getTotalRequests())
                .successfulRequests(requestLoggingFilter.getSuccessfulRequests())
                .failedRequests(requestLoggingFilter.getFailedRequests())
                .build();
    }
}

