package by.kopyshev.university.exception;

import org.springframework.http.HttpStatus;

public enum ErrorType {
    APPLICATION_ERROR("error.applicationError", HttpStatus.INTERNAL_SERVER_ERROR),
    DATA_NOT_FOUND("error.dataNotFound", HttpStatus.UNPROCESSABLE_ENTITY),
    VALIDATION_ERROR("error.validationError", HttpStatus.CONFLICT),
    WRONG_REQUEST("error.wrongRequest", HttpStatus.BAD_REQUEST);

    private final String errorCode;
    private final HttpStatus status;

    ErrorType(String errorCode, HttpStatus status) {
        this.errorCode = errorCode;
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
