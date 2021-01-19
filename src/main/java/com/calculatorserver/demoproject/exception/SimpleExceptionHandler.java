package com.calculatorserver.demoproject.exception;

import com.calculatorserver.demoproject.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class SimpleExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleError(IllegalArgumentException ex) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ExceptionDto exceptionDto = new ExceptionDto(ex.getMessage(),
                badRequest);
        return new ResponseEntity<>(exceptionDto, badRequest);
    }

}
