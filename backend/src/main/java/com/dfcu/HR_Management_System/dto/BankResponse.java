/**
 * Created By Eng. Pius Obonyo
 * Date: 6/12/24
 * Time: 6:49 PM
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
public class BankResponse {

    private String responseCode;
    private String responseMessage;

}

