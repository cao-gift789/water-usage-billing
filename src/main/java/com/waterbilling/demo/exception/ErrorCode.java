package com.waterbilling.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Your current password is not correct", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_OTP(1009, "Your otp code is not correct !", HttpStatus.BAD_REQUEST),
    OTP_EXPIRED(1010, "Your otp is expired", HttpStatus.BAD_REQUEST ),
    NEWS_NOT_EXISTED(1011, "News is  not existed", HttpStatus.BAD_REQUEST),
    FACILITY_TYPE_NOT_EXIST(1012, "Facility type is not existed", HttpStatus.BAD_REQUEST),
    FACILITY_TYPE_DELETE_ERROR(1013, "Deletion failed: the record is being referenced elsewhere.", HttpStatus.CONFLICT),
    ACCOUNT_NOT_EXISTED(1014, "User don't have account", HttpStatus.BAD_REQUEST),
    SUPPORT_INFO_NOT_EXISTED(1015, "Support info is not existed", HttpStatus.BAD_REQUEST),
    FACILITY_NOT_EXIST(1016, "Facility not existed", HttpStatus.BAD_REQUEST),
    INVOICE_NOT_EXIST(1017,"Don't have any invoice", HttpStatus.BAD_REQUEST),
    REQUEST_NOT_EXIST(1018, "Join Request not existed", HttpStatus.BAD_REQUEST),

    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
