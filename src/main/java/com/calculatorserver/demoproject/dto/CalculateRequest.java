package com.calculatorserver.demoproject.dto;

import lombok.Data;

@Data
public class CalculateRequest {

    private String expression;
    private Integer precision;

}
