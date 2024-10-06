/**
 * Created By Eng. Pius Obonyo
 * Date: 10/6/24
 * Time: 12:51 PM
 */

package com.dfcu.HR_Management_System.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetricsDto {
    private int totalRequests;
    private int successfulRequests;
    private int failedRequests;
}

