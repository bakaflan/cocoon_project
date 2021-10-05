package com.ruiling.cocoon.infrastructure.exception;

import static com.ruiling.cocoon.infrastructure.exception.ExceptionCode.RUNTIME_GENERATION_ERROR;

public class AppException extends BaseException {
    public AppException(ExceptionCode code, String message) {
        super(code, message);
    }

    public AppException(ExceptionCode code, Throwable cause) {
        super(code, cause);
    }

    public AppException(ExceptionCode code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public static AppException runtimeException(String message) {
        return new AppException(RUNTIME_GENERATION_ERROR, message);
    }

    public static AppException runtimeException(Throwable cause) {
        return new AppException(RUNTIME_GENERATION_ERROR, cause);
    }

    public static AppException runtimeException(String message, Throwable cause) {
        return new AppException(RUNTIME_GENERATION_ERROR, message, cause);
    }

    public static AppException unsupportedException(String message) {
        return new AppException(RUNTIME_GENERATION_ERROR, message);
    }
}
