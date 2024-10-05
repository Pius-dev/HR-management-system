package com.dfcu.HR_Management_System.service;

import com.dfcu.HR_Management_System.dto.MailDetails;

public interface EmailService {

    void sendEmailAlert(MailDetails mailDetails);
}
