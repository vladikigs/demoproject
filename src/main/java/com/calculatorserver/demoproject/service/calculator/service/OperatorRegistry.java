package com.calculatorserver.demoproject.service.calculator.service;


import com.calculatorserver.demoproject.service.calculator.entity.Operator;

import java.math.BigDecimal;
import java.util.Arrays;

public class OperatorRegistry {

    public Operator[] getOperators() {
        return operators;
    }

    private Operator[] operators = {
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
                .filter(o1 -> inputOperator.equals(o1.getOperator()))
                .findAny()
                .orElse(null);
    }

    public String calculationOperatorLogic(String numberOne, String numberTwo, Operator operator) {
        switch (operator.getOperator()) {
            case "+":
                return new BigDecimal(numberOne).add(new BigDecimal(numberTwo)).toString();
            case "-":
                return new BigDecimal(numberOne).subtract(new BigDecimal(numberTwo)).toString();
            case "/":
                if (new BigDecimal(numberTwo).equals(new BigDecimal(0))) {
                    throw new IllegalArgumentException("Деление на 0");
                } else {
                    return new BigDecimal(numberOne).divide(new BigDecimal(numberTwo),6,1).toString();
                }
            case "*":
                return new BigDecimal(numberOne).multiply(new BigDecimal(numberTwo)).toString();
            case "^":
                return String.valueOf(Math.pow(Double.parseDouble(numberOne), Double.parseDouble(numberTwo)));
        }
        return null;
    }
}
