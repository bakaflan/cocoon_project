package com.ruiling.cocoon.infrastructure.exception;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {
    private ExceptionCode code;

    public BaseException(ExceptionCode code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(ExceptionCode code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public BaseException(ExceptionCode code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
