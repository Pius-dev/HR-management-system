/**
 * Created By Eng. Pius Obonyo
 * Date: 6/14/24
 * Time: 6:22 PM
 */

package com.dfcu.HR_Management_System.service.Impl;

import com.dfcu.HR_Management_System.dto.MailDetails;
import com.dfcu.HR_Management_System.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service

public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Override
    public void sendEmailAlert(MailDetails mailDetails) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(senderEmail);
            message.setTo(mailDetails.getRecipient());
            message.setSubject(mailDetails.getSubject());
            message.setText(mailDetails.getMessageBody());

            emailSender.send(message);
            System.out.println("The alert sent successfully");
        } catch (MailException e) {
            throw new RuntimeException(e);
        }

    }
}

