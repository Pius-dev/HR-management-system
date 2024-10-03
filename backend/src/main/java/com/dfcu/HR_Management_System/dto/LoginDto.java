/**
 * Created By Eng. Pius Obonyo
 * Date: 6/21/24
 * Time: 11:13 PM
 */

package com.dfcu.HR_Management_System.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {

    private String employeeNumber;
    private String password;
}

