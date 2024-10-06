package com.dfcu.HR_Management_System.service;

import com.dfcu.HR_Management_System.dto.BankResponse;
import com.dfcu.HR_Management_System.dto.EmployeeRequest;
import com.dfcu.HR_Management_System.dto.PasswordChangeDto;
import com.dfcu.HR_Management_System.dto.StaffDto;
import com.dfcu.HR_Management_System.entity.User;

import java.util.List;

public interface AdminService {

    BankResponse addHRUser(EmployeeRequest employeeRequest);
    BankResponse changePassword(PasswordChangeDto passwordChangeDto);
    List<User> allStaffMembers();

    StaffDto getStaffByEmployeeNumber(String employeeNumber);
    BankResponse updateStaffDetails(Long staffId, EmployeeRequest employeeRequest);
}
