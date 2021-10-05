package com.ruiling.cocoon.infrastructure.config.web;

import com.ruiling.cocoon.infrastructure.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ruiling.cocoon.infrastructure.exception.ExceptionCode.RUNTIME_GENERATION_ERROR;

@Slf4j
@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorMessage> handleBaseException(BaseException ex, HttpServletRequest request) {
        log.warn("Error while access [{}]. {}", request.getRequestURI(), ex.getMessage(), ex);
        return new ResponseEntity<>(new ErrorMessage(ex), ex.getCode().getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        String url = webRequest.getContextPath();
        String message = ex.getMessage();
        if (webRequest instanceof ServletWebRequest) {
            HttpServletRequest request = ((ServletWebRequest) webRequest).getRequest();
            url = request.getRequestURI();
        }
        if (ex instanceof MethodArgumentNotValidException) {
            message = getValidationMessage((MethodArgumentNotValidException) ex);
        }
        log.warn("Error internal while access [{}]. {}", url, ex.getMessage(), ex);
        return new ResponseEntity<>(new ErrorMessage(RUNTIME_GENERATION_ERROR, message), headers, status);
    }

    private String getValidationMessage(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        return Stream.of(bindingResult.getGlobalErrors(), bindingResult.getFieldErrors())
                .flatMap(Collection::stream)
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .filter(StringUtils::isNotEmpty)
                .distinct()
                .collect(Collectors.joining("|"));
    }
}
