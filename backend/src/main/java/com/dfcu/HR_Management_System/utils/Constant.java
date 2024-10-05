/**
 * Created By Eng. Pius Obonyo
 * Date: 6/14/24
 * Time: 9:51 PM
 */

package com.dfcu.HR_Management_System.utils;

public class Constant {

    public static final String ACCOUNT_EXISTS_CODE = "001";
    public static final String ACCOUNT_EXISTS_MESSAGE = "This user already has an account created!";
    public static final String ACCOUNT_CREATION_SUCCESS_CODE = "002";
    public static final String ACCOUNT_CREATION_SUCCESS_MESSAGE = "Employee has been created successfully.";


    //Authentication and Authorization

    public static final String AUTHENTICATION_SUCCESS_CODE = "016";
    public static final String AUTHENTICATION_SUCCESS_MESSAGE = "Authentication successful.";
    public static final String AUTHENTICATION_FAILURE_CODE = "017";
    public static final String AUTHENTICATION_FAILURE_MESSAGE = "Authentication failed. Please check your credentials.";
    public static final String INVALID_VALIDATION_CODE = "018";
    public static final String INVALID_VALIDATION_CODE_MESSAGE = "The validation code must be a 10-digit number.";
    public static final String ACCOUNT_LOCKED_CODE = "019";
    public static final String ACCOUNT_LOCKED_MESSAGE = "Your account has been locked. Please contact customer support.";
    public static final String PASSWORD_RESET_SUCCESS_CODE = "020";
    public static final String PASSWORD_RESET_SUCCESS_MESSAGE = "Password has been reset successfully.";
    public static final String PASSWORD_RESET_FAILURE_CODE = "021";
    public static final String PASSWORD_RESET_FAILURE_MESSAGE = "Failed to reset the password. Please try again.";

    // General Errors

    public static final String SYSTEM_ERROR_CODE = "022";
    public static final String SYSTEM_ERROR_MESSAGE = "A system error has occurred. Please try again later.";
    public static final String INVALID_REQUEST_CODE = "023";
    public static final String INVALID_REQUEST_MESSAGE = "Invalid request.";
    public static final String DATA_NOT_FOUND_CODE = "024";
    public static final String DATA_NOT_FOUND_MESSAGE = "Requested data not found.";
    public static final String OPERATION_NOT_ALLOWED_CODE = "025";
    public static final String OPERATION_NOT_ALLOWED_MESSAGE = "This operation is not allowed.";
    public static final String SERVICE_UNAVAILABLE_CODE = "026";
    public static final String SERVICE_UNAVAILABLE_MESSAGE = "Service is currently unavailable. Please try again later.";


}

