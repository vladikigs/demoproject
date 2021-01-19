package com.calculatorserver.demoproject.entity;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Cache {

    @NonNull
    private String expression;
    @NonNull
    private Integer precision;
    private String resultOfExpression;

}
