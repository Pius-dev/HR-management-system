/**
 * Created By Eng. Pius Obonyo
 * Date: 6/14/24
 * Time: 11:01 PM
 */

package com.dfcu.HR_Management_System.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private final String errorCode;

    public CustomException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
