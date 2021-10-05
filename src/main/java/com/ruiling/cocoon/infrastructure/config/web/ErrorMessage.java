package com.ruiling.cocoon.infrastructure.config.web;

import com.ruiling.cocoon.infrastructure.exception.BaseException;
import com.ruiling.cocoon.infrastructure.exception.ExceptionCode;
import lombok.Getter;

@Getter
class ErrorMessage {
    private ExceptionCode code;
    private String message;

    public ErrorMessage(BaseException e) {
        this.code = e.getCode();
        this.message = e.getMessage();
    }

    public ErrorMessage(ExceptionCode code, String message) {
        this.code = code;
        this.message = message;
    }
}
