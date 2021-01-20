package com.calculatorserver.demoproject.entity;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
@Data
public class Cache {

    @NonNull
    private String expression;
    @NonNull
    private Integer precision;
    private String resultOfExpression;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cache cache = (Cache) o;
        return Objects.equals(expression, cache.expression) &&
                Objects.equals(precision, cache.precision) &&
                Objects.equals(resultOfExpression, cache.resultOfExpression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression, precision, resultOfExpression);
    }
}
