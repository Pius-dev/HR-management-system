/**
 * Created By Eng. Pius Obonyo
 * Date: 6/14/24
 * Time: 6:18 PM
 */

package com.dfcu.HR_Management_System.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailDetails {

    private String recipient;
    private String subject;
    private String messageBody;
    private String attachment;
}

