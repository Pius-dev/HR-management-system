/**
 * Created By Eng. Pius Obonyo
 * Date: 10/5/24
 * Time: 10:28 PM
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
public class PasswordChangeDto {

    private String employeeNumber;
    private String currentPassword;
    private String newPassword;
}

