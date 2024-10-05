/**
 * Created By Eng. Pius Obonyo
 * Date: 10/5/24
 * Time: 12:01 PM
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
public class AdminLoginDto {

    private String username;
    private String password;

}

