/**
 * Created By Eng. Pius Obonyo
 * Date: 10/5/24
 * Time: 11:33 PM
 */

package com.dfcu.HR_Management_System.dto;

import com.dfcu.HR_Management_System.entity.Role;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StaffDto {
    private Long id;
    private String surName;
    private String otherNames;
    private String employeeNumber;
    private LocalDate dateOfBirth;
    private String idPhotoBase64;
    private String email;
    private String status;
    private Role role;
}

