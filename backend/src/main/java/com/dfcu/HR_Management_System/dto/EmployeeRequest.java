/**
 * Created By Eng. Pius Obonyo
 * Date: 6/12/24
 * Time: 5:33 PM
 */

package com.dfcu.HR_Management_System.dto;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {

    private String surName;
    private String otherNames;
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String idPhotoBase64;
    private String validationCode;

}

