/**
 * Created By Eng. Pius Obonyo
 * Date: 6/12/24
 * Time: 4:41 PM
 */

package com.dfcu.HR_Management_System.entity;

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

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime modifiedAt;


}

