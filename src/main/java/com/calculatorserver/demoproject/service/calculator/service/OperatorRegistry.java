package com.calculatorserver.demoproject.service.calculator.service;

import com.calculatorserver.demoproject.service.calculator.entity.Operator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

@Component
public class OperatorRegistry {

    public Operator[] getOperators() {
        return operators;
    }

    private final Operator[] operators = {
            new Operator(0, "("),
            new Operator(0, ")"),
            new Operator(1, "+"),
            new Operator(1, "-"),
            new Operator(2, "*"),
            new Operator(2, "/"),
            new Operator(3, "^")
    };

    Operator searchOperator(String inputOperator) {
        return Arrays.stream(operators)
                .filter(o1 -> inputOperator.equals(o1.getMathOperator()))
                .findAny()
                .orElse(null);
    }

    public String calculationOperatorLogic(String numberOne, String numberTwo, Operator operator, Integer precision) {
        switch (operator.getMathOperator()) {
            case "+":
                return new BigDecimal(numberOne).add(new BigDecimal(numberTwo)).setScale(precision, RoundingMode.HALF_UP).toString();
            case "-":
                return new BigDecimal(numberOne).subtract(new BigDecimal(numberTwo)).setScale(precision, RoundingMode.HALF_UP).toString();
            case "/":
                if (new BigDecimal(numberTwo).equals(new BigDecimal(0))) {
                    throw new IllegalArgumentException("Деление на 0");
                } else {
                    return new BigDecimal(numberOne).divide(new BigDecimal(numberTwo), precision, RoundingMode.HALF_UP).toString();
                }
            case "*":
                return new BigDecimal(numberOne).multiply(new BigDecimal(numberTwo)).setScale(precision, RoundingMode.HALF_UP).toString();
            case "^":
                return String.valueOf(Math.pow(Double.parseDouble(numberOne), Double.parseDouble(numberTwo)));
            default: return null;
        }
    }
}
