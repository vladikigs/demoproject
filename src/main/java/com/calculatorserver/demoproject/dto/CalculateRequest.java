package com.calculatorserver.demoproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@AllArgsConstructor
@EqualsAndHashCode
public class CalculateRequest {

    private String expression;
    private Integer precision;

}
