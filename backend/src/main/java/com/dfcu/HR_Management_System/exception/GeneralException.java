/**
 * Created By Eng. Pius Obonyo
 * Date: 6/17/24
 * Time: 10:15 PM
 */

package com.dfcu.HR_Management_System.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class GeneralException extends Exception {

    private int returnCode = 100;

    public GeneralException(String s) {
        super(s);
    }

    public GeneralException(int i, String s) {
        super(s);
        this.setReturnCode(i);
    }

}


