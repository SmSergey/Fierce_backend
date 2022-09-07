package com.app.fierce_backend.common.advices;

import com.app.fierce_backend.common.ApiResponse;
import com.app.fierce_backend.common.interfaces.ErrorMessages;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;

@ControllerAdvice
public class GlobalAdvice {

    Logger logger = LoggerFactory.getLogger(GlobalAdvice.class);

    @ExceptionHandler(BindException.class)
    public ResponseEntity<String> handleValidationErrors(BindException err) {
        val filedError = err.getFieldErrors().get(0);
        return new ApiResponse()
                .setMessage(filedError.getField() + " : " + filedError.getDefaultMessage())
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleError(EntityNotFoundException err) {
        logger.error(err.getMessage());
        return new ApiResponse()
                .setStatus(HttpStatus.NOT_FOUND.value())
                .setMessage(ErrorMessages.NOT_FOUND).build();
    }


    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleError(ValidationException err) {
        logger.error(err.getMessage());
        return new ApiResponse()
                .setStatus(HttpStatus.BAD_REQUEST.value())
                .setMessage(err.getMessage()).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleError(Exception err) {
        logger.error(err.getMessage());
        return new ApiResponse()
                .setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setMessage(ErrorMessages.INTERNAL_SERVER_ERROR).build();
    }
}
