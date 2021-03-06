package com.calculatorserver.demoproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ExceptionDto {

    private String message;
    private HttpStatus httpStatus;

}
