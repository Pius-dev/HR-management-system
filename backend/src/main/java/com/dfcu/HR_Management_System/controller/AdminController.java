/**
 * Created By Eng. Pius Obonyo
 * Date: 10/5/24
 * Time: 10:35 PM
 */

package com.dfcu.HR_Management_System.controller;

import com.dfcu.HR_Management_System.dto.*;
import com.dfcu.HR_Management_System.entity.User;
import com.dfcu.HR_Management_System.service.AdminService;
import com.dfcu.HR_Management_System.service.Impl.MetricsService;
import com.dfcu.HR_Management_System.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;
    private final MetricsService metricsService;

    @PostMapping("/addHRAdmin")
    public BankResponse addHRAdmin(@RequestBody EmployeeRequest employeeRequest) {
        return adminService.addHRUser(employeeRequest);
    }

    @GetMapping("/employee/{employeeNumber}")
    public ResponseEntity<StaffDto> retrieveStaff(@PathVariable String employeeNumber) {
        StaffDto staffDto = adminService.getStaffByEmployeeNumber(employeeNumber);
        if (staffDto != null) {
            return ResponseEntity.ok(staffDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{staffId}")
    public BankResponse updateStaffDetails(
            @PathVariable Long staffId,
            @ModelAttribute EmployeeRequest employeeRequest) {

        return adminService.updateStaffDetails(staffId, employeeRequest);
    }

    @GetMapping("/allStaff")
    public GenericResponse allStaffMembers() {
        List<User> staffMembers = adminService.allStaffMembers();
        return GenericResponse.builder()
                .responseCode(Constant.SUCCESS_DATA_FOUND_CODE)
                .data(staffMembers)
                .build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")  // Only admins can access this
    @GetMapping("/metrics")
    public ResponseEntity<MetricsDto> getApiMetrics() {
        MetricsDto metrics = metricsService.getApiMetrics();
        return ResponseEntity.ok(metrics);
    }
}

