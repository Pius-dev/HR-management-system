/**
 * Created By Eng. Pius Obonyo
 * Date: 10/5/24
 * Time: 12:03 PM
 */

package com.dfcu.HR_Management_System.service;

import com.dfcu.HR_Management_System.dto.BankResponse;
import com.dfcu.HR_Management_System.dto.EmployeeRequest;

public interface EmployeeService {

    BankResponse registerStaff(EmployeeRequest employeeRequest);
}

