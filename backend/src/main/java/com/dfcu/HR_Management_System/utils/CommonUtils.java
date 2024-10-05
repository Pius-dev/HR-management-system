/**
 * Created By Eng. Pius Obonyo
 * Date: 6/12/24
 * Time: 10:08 PM
 */

package com.dfcu.HR_Management_System.utils;

import com.dfcu.HR_Management_System.dto.EmployeeRequest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

public class CommonUtils {

    /**
     * generate employee number with first letter of othername
     * and first letter of surname + 4 random digits eg: PO8756
     */
    public static String generateEmployeeNumber(EmployeeRequest request) {

        String otherName = request.getOtherNames();
        String surname = request.getSurName();

        // Get first letter of other name and first letter of surname
        String firstLetterOfOtherName = otherName.substring(0, 1).toUpperCase();
        String firstLetterOfSurname = surname.substring(0, 1).toUpperCase();

        // Generate a 4-digit random number
        Random random = new Random();
        int randomNumber = 1000 + random.nextInt(9000);

        // Combine the letters and the random number to form the employee number

        return firstLetterOfOtherName + firstLetterOfSurname + randomNumber;
    }

    public static Timestamp getCurrentTimeStamp() {
        return new Timestamp(new Date().getTime());
    }

    /**
     * Generates a unique 10-digit code.
     *
     * @return the generated code.
     */
    public String generateUniqueCode() {
        // Generate a random 10-digit number
        Random random = new Random();
        int code = 1000000000 + random.nextInt(900000000); // Ensure it's 10 digits
        return String.valueOf(code);
    }


}

