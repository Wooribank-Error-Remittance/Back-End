package com.wooribank.backend.constant;

import com.wooribank.backend.exception.CommonException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS("0000", "Success."),

    // 1xxx is signup error
    EMAIL_ALREADY_EXISTED("1000", "Email is already existed"),
    PHONE_NUMBER_ALREADY_EXISTED("1001", "Phone number is already existed"),
    RETURN_REQUEST_ALREADY_EXISTED("1002", "Return Request is already existed"),
    SIGN_UP_FAILED_FOR_INVALID_INFO("1010", "Sign up failed for invalid info"),

    USER_NOT_EXISTED("1100", "user not existed."),
    BANK_NOT_EXISTED("1101", "bank not existed."),
    ACCOUNT_NOT_EXISTED("1102", "account not existed."),
    TRANSACTION_NOT_EXISTED("1103", "transaction not existed."),
    RETURN_REQUEST_NOT_EXISTED("1103", "return_request not existed."),

    INVALID_PHONE_NUMBER("1200", "Invalid phone number"),
    INVALID_PASSWORD("1201", "Invalid password"),

    INVALID_API_RESPONSE("8000", "Invalid Api Response"),
    UNAUTHORIZED_ERROR("9998", "Unauthorized error"),
    UNKNOWN_ERROR("9999", "Unknown Error");

    private final String code;
    private final String message;

    public static ResponseCode find(String code) {
        for (ResponseCode responseCode : values()) {
            if (responseCode.getCode().equals(code)) {
                return responseCode;
            }
        }
        throw new CommonException(code);
    }
}
