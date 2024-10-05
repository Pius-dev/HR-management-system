/**
 * Created By Eng. Pius Obonyo
 * Date: 10/5/24
 * Time: 12:18 PM
 */

package com.dfcu.HR_Management_System.service.Impl;

import com.dfcu.HR_Management_System.dto.BankResponse;
import com.dfcu.HR_Management_System.dto.EmployeeRequest;
import com.dfcu.HR_Management_System.dto.MailDetails;
import com.dfcu.HR_Management_System.dto.UserInfo;
import com.dfcu.HR_Management_System.entity.Role;
import com.dfcu.HR_Management_System.entity.User;
import com.dfcu.HR_Management_System.exception.CustomException;
import com.dfcu.HR_Management_System.repository.UserRepository;
import com.dfcu.HR_Management_System.service.EmailService;
import com.dfcu.HR_Management_System.service.EmployeeService;
import com.dfcu.HR_Management_System.utils.CommonUtils;
import com.dfcu.HR_Management_System.utils.Constant;
import com.dfcu.HR_Management_System.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {


    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_STRING = "Authorization";
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final JwtUtil jwtUtil;

    @Override
    public BankResponse registerStaff(EmployeeRequest employeeRequest) {
        try {
            if (userRepository.existsByEmail(employeeRequest.getEmail())) {
                return BankResponse.builder()
                        .responseCode(Constant.ACCOUNT_EXISTS_CODE)
                        .responseMessage(Constant.ACCOUNT_EXISTS_MESSAGE)
                        .build();
            }

            // Validate if the provided validation code is exactly 10 digits
            if (employeeRequest.getValidationCode() == null || !employeeRequest.getValidationCode().matches("\\d{10}")) {
                return BankResponse.builder()
                        .responseCode(Constant.INVALID_VALIDATION_CODE)
                        .responseMessage(Constant.INVALID_VALIDATION_CODE_MESSAGE)
                        .build();
            }
            User newUser = User.builder()
                    .surName(employeeRequest.getSurName())
                    .otherNames(employeeRequest.getOtherNames())
                    .email(employeeRequest.getEmail())
                    .dateOfBirth(employeeRequest.getDateOfBirth())
                    .validationCode(employeeRequest.getValidationCode())
                    .idPhotoBase64(employeeRequest.getIdPhotoBase64())
                    .employeeNumber(CommonUtils.generateEmployeeNumber(employeeRequest))
                    .password(passwordEncoder.encode(employeeRequest.getPassword()))
                    .role(Role.valueOf("ROLE_USER"))
                    .status("Active")
                    .isFirstTimeLogin(Boolean.TRUE)
                    .build();

            var savedUser = userRepository.save(newUser);

            var EmployeeName = savedUser.getOtherNames() + " " + savedUser.getSurName();

            // send out staff register alert on success
            MailDetails emailDetails = MailDetails.builder()
                    .recipient(savedUser.getEmail())
                    .subject("Welcome to Dfcu Bank Ltd: Your HR Staff Account Has Been Created")
                    .messageBody(
                            "Dear " + EmployeeName + ",\n\n" +
                                    "Your staff account at Dfcu Bank Ltd has been successfully created.\n\n" +
                                    "Here are your account details:\n\n" +
                                    "Employee Number: " + savedUser.getEmployeeNumber() + "\n" +
                                    "Validation Code: " + savedUser.getValidationCode() + "\n\n" +
                                    "Please use the validation code for account verification during your first login.\n\n" +
                                    "Best regards,\n" +
                                    "Dfcu Bank Ltd\n"

                    )
                    .build();

            emailService.sendEmailAlert(emailDetails);
            return BankResponse.builder()
                    .responseCode(Constant.ACCOUNT_CREATION_SUCCESS_CODE)
                    .responseMessage(Constant.ACCOUNT_CREATION_SUCCESS_MESSAGE)
                    .userInfo(UserInfo.builder()
                            .surname(savedUser.getSurName())
                            .otherNames(savedUser.getOtherNames())
                            .employeeNumber(savedUser.getEmployeeNumber())
                            .build())
                    .build();

        } catch (Exception e) {
            throw new CustomException(Constant.SYSTEM_ERROR_CODE, Constant.SYSTEM_ERROR_MESSAGE);
        }
    }
}

