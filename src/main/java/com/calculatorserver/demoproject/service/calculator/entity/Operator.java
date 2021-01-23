package com.calculatorserver.demoproject.service.calculator.entity;

public class Operator {
    private final int priority;
    private final String mathOperator;

    public int getPriority() {
        return priority;
    }

    public String getMathOperator() {
        return mathOperator;
    }

    public Operator(int priority, String mathOperator) {
        this.priority = priority;
        this.mathOperator = mathOperator;
    }
}
