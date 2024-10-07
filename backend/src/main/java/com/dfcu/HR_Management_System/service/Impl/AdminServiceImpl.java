/**
 * Created By Eng. Pius Obonyo
 * Date: 10/5/24
 * Time: 9:06 PM
 */

package com.dfcu.HR_Management_System.service.Impl;

import com.dfcu.HR_Management_System.dto.*;
import com.dfcu.HR_Management_System.entity.Role;
import com.dfcu.HR_Management_System.entity.User;
import com.dfcu.HR_Management_System.exception.CustomException;
import com.dfcu.HR_Management_System.repository.UserRepository;
import com.dfcu.HR_Management_System.service.AdminService;
import com.dfcu.HR_Management_System.service.EmailService;
import com.dfcu.HR_Management_System.utils.CommonUtils;
import com.dfcu.HR_Management_System.utils.Constant;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    @Value("${spring.adminPassword}")
    private String adminPassword;

    @Value("${spring.adminEmail}")

    private String adminEmail;
    @Value("${spring.adminUsername}")

    private String adminUsername;

    @PostConstruct
    public void createAdminAccount() {
        User adminAccount = userRepository.findByRole(Role.ROLE_ADMIN);
        if (adminAccount == null) {
            User admin = new User();
            admin.setEmail(adminEmail);
            admin.setSurName("Admin");
            admin.setOtherNames("Admin");
            admin.setEmployeeNumber(adminUsername);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setRole(Role.ROLE_ADMIN);
            userRepository.save(admin);

        }
    }

    @Override
    public BankResponse addHRUser(EmployeeRequest employeeRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInEmail = authentication.getName();

        // Check if the logged-in user is an admin
        Optional<User> adminUser = userRepository.findByEmail(loggedInEmail);

        if (adminUser.isPresent() && adminUser.get().getRole().equals(Role.ROLE_ADMIN)) {
            // Check if the HR user already exists
            if (userRepository.existsByEmail(employeeRequest.getEmail())) {
                return BankResponse.builder()
                        .responseCode(Constant.ACCOUNT_EXISTS_CODE)
                        .responseMessage("HR user already exists with this email")
                        .build();
            }

            // Create the new HR user
            User hrUser = User.builder()
                    .surName(employeeRequest.getSurName())
                    .otherNames(employeeRequest.getOtherNames())
                    .email(employeeRequest.getEmail())
                    .employeeNumber(CommonUtils.generateEmployeeNumber(employeeRequest))
                    .password(passwordEncoder.encode(CommonUtils.generateRandomPassword(8)))
                    .role(Role.ROLE_HR)
                    .status("Active")
                    .build();

            // Save the HR user to the database
            var savedUser = userRepository.save(hrUser);

            var EmployeeName = savedUser.getOtherNames() + " " + savedUser.getSurName();

            // send out staff register alert on success
            MailDetails emailDetails = MailDetails.builder()
                    .recipient(savedUser.getEmail())
                    .subject("Welcome to Dfcu Bank Ltd: Your HR Staff Account Has Been Created")
                    .messageBody(
                            "Dear " + EmployeeName + ",\n\n" +
                                    "Your admin account at Dfcu HR system has been successfully created.\n\n" +
                                    "Here are your details:\n\n" +
                                    "Employee Number: " + savedUser.getEmployeeNumber() + "\n" +
                                    "Your Password: " + savedUser.getPassword() + "\n\n" +
                                    "Best regards,\n" +
                                    "Dfcu Bank Ltd\n"

                    )
                    .build();

            emailService.sendEmailAlert(emailDetails);

            return BankResponse.builder()
                    .responseCode(Constant.ACCOUNT_CREATION_SUCCESS_CODE)
                    .responseMessage("HR user created successfully")
                    .build();

        } else {
            // If the user is not an admin, return an unauthorized response
            return BankResponse.builder()
                    .responseCode(Constant.AUTH_UNAUTHORIZED_ACCESS_CODE)
                    .responseMessage(Constant.AUTH_UNAUTHORIZED_ACCESS_MESSAGE)
                    .build();
        }
    }

    @Override
    public BankResponse changePassword(PasswordChangeDto passwordChangeDto) {
        Optional<User> optionalUser = userRepository.findFirstByEmail(passwordChangeDto.getEmployeeNumber());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Validate current password
            if (!passwordEncoder.matches(passwordChangeDto.getCurrentPassword(), user.getPassword())) {
                return BankResponse.builder()
                        .responseCode("Incorrect Password")
                        .responseMessage("The current password is incorrect.")
                        .build();
            }

            // Set new password and update flags
            user.setPassword(passwordEncoder.encode(passwordChangeDto.getNewPassword()));
            user.setIsFirstTimePassword(false);  // Mark the password as changed
            userRepository.save(user);

            return BankResponse.builder()
                    .responseCode("Password Change Success")
                    .responseMessage("Your password has been changed successfully.")
                    .build();
        } else {
            throw new CustomException("User not found", "The provided employee number does not exist.");
        }
    }


    @Override
    public List<User> allStaffMembers() {
        return userRepository.findAll();

    }

    @Override
    @Transactional
    public StaffDto getStaffByEmployeeNumber(String employeeNumber) {
        Optional<User> staffMember = Optional.ofNullable(userRepository.findByEmployeeNumber(employeeNumber));
        return staffMember.map(User::getDto).orElse(null);
    }

    @Override
    @Transactional
    public StaffDto getStaffById(Long staffId) {
        Optional<User> optionalUser = userRepository.findById(staffId);
        if (optionalUser.isPresent()) {
            return optionalUser.get().getDto();
        }
        return null;
    }


     @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_HR')")
    @Override
    @Transactional
    public BankResponse updateStaffDetails(Long staffId, EmployeeRequest employeeRequest) {
        Optional<User> optionalUser = userRepository.findById(staffId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            user.setSurName(employeeRequest.getSurName());
            user.setOtherNames(employeeRequest.getOtherNames());
            user.setEmail(employeeRequest.getEmail());
            user.setDateOfBirth(employeeRequest.getDateOfBirth());
            user.setIdPhotoBase64(employeeRequest.getIdPhotoBase64());

            userRepository.save(user);
            return BankResponse.builder()
                    .responseCode("Update Success")
                    .responseMessage("Staff member details updated successfully.")
                    .build();
        } else {
            return BankResponse.builder()
                    .responseCode("Not Found")
                    .responseMessage("Staff member not found.")
                    .build();
        }
    }




}

