/**
 * Created By Eng. Pius Obonyo
 * Date: 6/14/24
 * Time: 9:51 PM
 */

package com.dfcu.HR_Management_System.utils;

public class Constant {

    // Account Management
    public static final String ACCOUNT_EXISTS_CODE = "400";
    public static final String ACCOUNT_EXISTS_MESSAGE = "This user already has an account created!";
    public static final String ACCOUNT_CREATION_SUCCESS_CODE = "201";
    public static final String ACCOUNT_CREATION_SUCCESS_MESSAGE = "Employee has been created successfully.";

    // Authentication and Authorization
    public static final String AUTH_UNAUTHORIZED_ACCESS_CODE = "401";
    public static final String AUTH_UNAUTHORIZED_ACCESS_MESSAGE = "Unauthorized access: Admin privileges required.";
    public static final String AUTH_FORBIDDEN_CODE = "403";
    public static final String AUTH_FORBIDDEN_MESSAGE = "Access is forbidden. You do not have the required permissions.";
    public static final String AUTH_FAILURE_CODE = "401";
    public static final String AUTH_FAILURE_MESSAGE = "Authentication failed. Please check your credentials.";
    public static final String AUTH_INVALID_VALIDATION_CODE = "400";
    public static final String AUTH_INVALID_VALIDATION_MESSAGE = "Invalid validation code. Must be a 10-digit number.";
    public static final String AUTH_ACCOUNT_LOCKED_CODE = "403";
    public static final String AUTH_ACCOUNT_LOCKED_MESSAGE = "Account locked. Please contact customer support.";

    // Password Management
    public static final String PASSWORD_RESET_SUCCESS_CODE = "200";
    public static final String PASSWORD_RESET_SUCCESS_MESSAGE = "Password reset successfully.";
    public static final String PASSWORD_RESET_FAILURE_CODE = "400";
    public static final String PASSWORD_RESET_FAILURE_MESSAGE = "Password reset failed. Please try again.";

    // General Success Responses
    public static final String SUCCESS_DATA_FOUND_CODE = "200";
    public static final String SUCCESS_DATA_FOUND_MESSAGE = "Requested data found.";

    // General Error Responses
    public static final String ERROR_SYSTEM_CODE = "500";
    public static final String ERROR_SYSTEM_MESSAGE = "A system error has occurred. Please try again later.";
    public static final String ERROR_INVALID_REQUEST_CODE = "400";
    public static final String ERROR_INVALID_REQUEST_MESSAGE = "Invalid request. Please check the request parameters.";
    public static final String ERROR_OPERATION_NOT_ALLOWED_CODE = "403";
    public static final String ERROR_OPERATION_NOT_ALLOWED_MESSAGE = "Operation not allowed.";
    public static final String ERROR_SERVICE_UNAVAILABLE_CODE = "503";
    public static final String ERROR_SERVICE_UNAVAILABLE_MESSAGE = "Service is currently unavailable. Please try again later.";

    // Not Found Response
    public static final String NOT_FOUND_CODE = "404";
    public static final String NOT_FOUND_MESSAGE = "The requested resource was not found.";
}


