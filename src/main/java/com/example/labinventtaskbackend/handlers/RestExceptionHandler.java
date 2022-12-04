package com.example.labinventtaskbackend.handlers;

import com.example.labinventtaskbackend.exception.OperationFailedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(OperationFailedException.class)
    public ResponseEntity<Object> handleOperationFailedException(OperationFailedException exception) {
        ApiError apiError = new ApiError(HttpStatus.EXPECTATION_FAILED, exception.getMessage(), exception);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        return buildResponseEntity(apiError);
    }
    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
