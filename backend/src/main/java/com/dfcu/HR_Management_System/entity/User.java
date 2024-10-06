/**
 * Created By Eng. Pius Obonyo
 * Date: 6/12/24
 * Time: 4:41 PM
 */

package com.dfcu.HR_Management_System.entity;

import com.dfcu.HR_Management_System.dto.StaffDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "system_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String surName;
    private String otherNames;
    private String employeeNumber;
    private LocalDate dateOfBirth;
    @Lob
    private String idPhotoBase64;
    private String validationCode;
    private String email;
    private String password;
    private String status;
    private Role role;
    private Boolean isFirstTimeLogin;
    private Boolean isFirstTimePassword;


    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime modifiedAt;


    public StaffDto getDto() {
        StaffDto staffDto = new StaffDto();

        staffDto.setId(id);
        staffDto.setSurName(surName);
        staffDto.setOtherNames(otherNames);
        staffDto.setEmployeeNumber(employeeNumber);
        staffDto.setDateOfBirth(dateOfBirth);
        staffDto.setEmail(email);
        staffDto.setIdPhotoBase64(idPhotoBase64);
        staffDto.setRole(role);
        staffDto.setStatus(status);

        return staffDto;
    }


}

