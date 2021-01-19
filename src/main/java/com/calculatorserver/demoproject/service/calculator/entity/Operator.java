package com.calculatorserver.demoproject.service.calculator.entity;

public class Operator {
    private int priority;
    private String operator;

    public int getPriority() {
        return priority;
    }

    public String getOperator() {
        return operator;
    }

    public Operator(int priority, String operator) {
        this.priority = priority;
        this.operator = operator;
    }
}
