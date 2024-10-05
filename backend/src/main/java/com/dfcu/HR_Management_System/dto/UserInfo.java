/**
 * Created By Eng. Pius Obonyo
 * Date: 10/4/24
 * Time: 5:34 PM
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
public class UserInfo {

    private String surname;
    private String otherNames;
    private String employeeNumber;

}

