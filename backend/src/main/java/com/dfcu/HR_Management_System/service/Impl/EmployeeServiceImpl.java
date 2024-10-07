/**
 * Created By Eng. Pius Obonyo
 * Date: 10/5/24
 * Time: 12:18 PM
 */

package com.dfcu.HR_Management_System.service.Impl;

import com.dfcu.HR_Management_System.dto.*;
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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Optional;

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
                        .responseCode(Constant.AUTH_INVALID_VALIDATION_CODE)
                        .responseMessage(Constant.AUTH_INVALID_VALIDATION_MESSAGE)
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

            System.out.println( "Alert sent ");

           // emailService.sendEmailAlert(emailDetails);
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
            throw new CustomException(Constant.ERROR_SYSTEM_CODE, Constant.ERROR_SYSTEM_MESSAGE);
        }
    }

    @Override
    public BankResponse login(LoginDto loginDto, HttpServletResponse httpServletResponse) {
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmployeeNumber(), loginDto.getPassword()));

            // Retrieve user details from the database
            final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getEmployeeNumber());
            Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmployeeNumber(userDetails.getUsername()));

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();

                // Check if it's the user's first time login
                if (Boolean.TRUE.equals(user.getIsFirstTimeLogin())) {
                    // Prompt for validation code and check if it's provided
                    if (loginDto.getValidationCode() == null || !loginDto.getValidationCode().equals(user.getValidationCode())) {
                        return BankResponse.builder()
                                .responseCode("Validation Required")
                                .responseMessage("Please provide the correct validation code sent to your email.")
                                .build();
                    }

                    // If validation is successful, update the first-time login flag
                    user.setIsFirstTimeLogin(Boolean.FALSE);
                    userRepository.save(user);  // Save the updated user info
                }

                // Check if it's the HR user's first time using the generated password
                if (user.getRole() == Role.ROLE_HR && Boolean.TRUE.equals(user.getIsFirstTimePassword())) {
                    // Prompt for password change
                    return BankResponse.builder()
                            .responseCode("First Time Password Change Required")
                            .responseMessage("Please change your password as this is your first time login.")
                            .build();
                }

                // Generate JWT after successful login and validation
                final String jwt = jwtUtil.generateToken(userDetails.getUsername());

                // Prepare the response JSON
                JSONObject jsonResponse = new JSONObject()
                        .put("userId", optionalUser.get().getId())
                        .put("role", user.getRole()); // Ensure you retrieve the role from the User object

                // Set the necessary response headers
                httpServletResponse.addHeader("Access-Control-Expose-Headers", "Authorization");
                httpServletResponse.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER,Origin, " +
                        "X-Requested-With, Content-Type, Accept, X-Custom-header");
                httpServletResponse.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);

                // Write the JSON response
                httpServletResponse.getWriter().write(jsonResponse.toString());

                return BankResponse.builder()
                        .responseCode("Login Success")
                        .responseMessage("User logged in successfully")
                        .build();
            } else {
                throw new CustomException("User not found", "The provided employee number does not exist.");
            }
        } catch (Exception e) {
            throw new CustomException(Constant.ERROR_SYSTEM_CODE, Constant.ERROR_SYSTEM_MESSAGE);
        }
    }



}

