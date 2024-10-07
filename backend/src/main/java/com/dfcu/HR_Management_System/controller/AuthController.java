/**
 * Created By Eng. Pius Obonyo
 * Date: 6/23/24
 * Time: 6:41 PM
 */

package com.dfcu.HR_Management_System.controller;

import com.dfcu.HR_Management_System.dto.BankResponse;
import com.dfcu.HR_Management_System.dto.EmployeeRequest;
import com.dfcu.HR_Management_System.dto.LoginDto;
import com.dfcu.HR_Management_System.dto.PasswordChangeDto;
import com.dfcu.HR_Management_System.service.AdminService;
import com.dfcu.HR_Management_System.service.EmployeeService;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final EmployeeService employeeService;
    private final AdminService adminService;


    @PostMapping("/registerStaff")
    public BankResponse registerStaff(@RequestBody EmployeeRequest employeeRequest) {
        return employeeService.registerStaff(employeeRequest);
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginDto loginDto, HttpServletResponse httpServletResponse) throws IOException {
        BankResponse response = employeeService.login(loginDto, httpServletResponse);
    }


    @PostMapping("/change-password")
    public BankResponse changePassword(@RequestBody PasswordChangeDto passwordChangeDto) {
        return adminService.changePassword(passwordChangeDto);
    }

}

