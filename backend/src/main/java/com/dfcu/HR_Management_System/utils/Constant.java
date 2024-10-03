/**
 * Created By Eng. Pius Obonyo
 * Date: 6/14/24
 * Time: 9:51 PM
 */

package com.dfcu.HR_Management_System.utils;

public class Constant {

    //Account Operations
    public static final String ACCOUNT_EXISTS_CODE = "001";
    public static final String ACCOUNT_EXISTS_MESSAGE = "This user already has an account created!";
    public static final String ACCOUNT_CREATION_SUCCESS_CODE = "002";
    public static final String ACCOUNT_CREATION_SUCCESS_MESSAGE = "Account has been created successfully.";
    public static final String ACCOUNT_CREATION_FAILURE_CODE = "003";
    public static final String ACCOUNT_CREATION_FAILURE_MESSAGE = "Failed to create the account. Please try again.";
    public static final String ACCOUNT_NOT_FOUND_CODE = "004";
    public static final String ACCOUNT_NOT_FOUND_MESSAGE = "Account not found.";

    public static final String INVALID_AMOUNT_CODE = "036";
    public static final String INVALID_AMOUNT_MESSAGE = "The amount to be credited must be greater than zero.";

    public static final String INVALID_DEBIT_AMOUNT_CODE = "037";
    public static final String INVALID_DEBIT_AMOUNT_MESSAGE = "The amount to be debited must be greater than zero.";
    public static final String ACCOUNT_UPDATE_SUCCESS_CODE = "005";
    public static final String ACCOUNT_UPDATE_SUCCESS_MESSAGE = "Account has been updated successfully.";
    public static final String ACCOUNT_UPDATE_FAILURE_CODE = "006";
    public static final String ACCOUNT_UPDATE_FAILURE_MESSAGE = "Failed to update the account. Please try again.";
    public static final String ACCOUNT_FOUND_CODE = "007";
    public static final String ACCOUNT_FOUND_MESSAGE = "Account found.";
    public static final String ACCOUNT_DELETION_FAILURE_CODE = "008";
    public static final String ACCOUNT_DELETION_FAILURE_MESSAGE = "Failed to delete the account. Please try again.";

    // General Transaction Messages
    public static final String TRANSACTION_SUCCESS_CODE = "009";
    public static final String TRANSACTION_SUCCESS_MESSAGE = "Transaction completed successfully.";
    public static final String TRANSACTION_FAILURE_CODE = "010";
    public static final String TRANSACTION_FAILURE_MESSAGE = "Transaction failed. Please try again.";
    public static final String INSUFFICIENT_FUNDS_CODE = "011";
    public static final String INSUFFICIENT_FUNDS_MESSAGE = "Insufficient funds to complete the transaction.";
    public static final String INVALID_TRANSACTION_CODE = "012";
    public static final String INVALID_TRANSACTION_MESSAGE = "Invalid transaction request.";
    public static final String TRANSACTION_NOT_FOUND_CODE = "013";
    public static final String TRANSACTION_NOT_FOUND_MESSAGE = "Transaction not found.";
    public static final String TRANSACTION_PENDING_CODE = "014";
    public static final String TRANSACTION_PENDING_MESSAGE = "Transaction is pending.";
    public static final String TRANSACTION_CANCELED_CODE = "015";
    public static final String TRANSACTION_CANCELED_MESSAGE = "Transaction has been canceled.";

    // Debit Transaction Messages
    public static final String DEBIT_SUCCESS_CODE = "027";
    public static final String DEBIT_SUCCESS_MESSAGE = "Debit transaction completed successfully.";
    public static final String DEBIT_FAILURE_CODE = "028";
    public static final String DEBIT_FAILURE_MESSAGE = "Debit transaction failed. Please try again.";

    // Credit Transaction Messages
    public static final String CREDIT_SUCCESS_CODE = "029";
    public static final String CREDIT_SUCCESS_MESSAGE = "Credit transaction completed successfully.";
    public static final String CREDIT_FAILURE_CODE = "030";
    public static final String CREDIT_FAILURE_MESSAGE = "Credit transaction failed. Please try again.";


    //Authentication and Authorization

    public static final String AUTHENTICATION_SUCCESS_CODE = "016";
    public static final String AUTHENTICATION_SUCCESS_MESSAGE = "Authentication successful.";
    public static final String AUTHENTICATION_FAILURE_CODE = "017";
    public static final String AUTHENTICATION_FAILURE_MESSAGE = "Authentication failed. Please check your credentials.";
    public static final String AUTHORIZATION_FAILURE_CODE = "018";
    public static final String AUTHORIZATION_FAILURE_MESSAGE = "You do not have permission to perform this action.";
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

