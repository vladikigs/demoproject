package com.calculatorserver.demoproject.service.calculator.service;

import com.calculatorserver.demoproject.service.calculator.entity.Operator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;


public class ReversePolishNotation {

    private Deque<String> stackOperators = new ArrayDeque<String>();

    private LinkedList<String> output = new LinkedList<>();
    private Validator validator;
    private OperatorRegistry operatorRegistry;

    public ReversePolishNotation(Validator validator, OperatorRegistry operatorRegistry) {
        this.validator = validator;
        this.operatorRegistry = operatorRegistry;
    }


    public LinkedList<String> parse(String inputString) {
        output = new LinkedList<>();
        validator.validate(inputString.replaceAll("[ ]+", " "));
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < inputString.length(); i++) {

            switch (inputString.charAt(i)) {
                case '(':
                    stackOperators.push("(");
                    output.add("(");
                    break;
                case ')':
                    addLast(stringBuffer.toString());
                    stringBuffer = new StringBuilder();
                    moveOperatorsFromStackToInputString(i);
                    output.add(")");
                    break;
                default:
                    if (Character.isDigit(inputString.charAt(i)) || inputString.charAt(i) == '.') {
                        stringBuffer.append(inputString.charAt(i));
                    } else if (inputString.charAt(i) != ' ') {
                        addLast(stringBuffer.toString());
                        stringBuffer = new StringBuilder();
                        pushOperator(operatorRegistry.searchOperator(String.valueOf(inputString.charAt(i))));
                    }

            }
        }
        addLast(stringBuffer.toString());
        popOperatorsToOutputString();
        return output;
    }

    private void popOperatorsToOutputString() {
        while (!stackOperators.isEmpty()) {
            output.add(stackOperators.pop());
        }

    }

    private void addLast(String last) {
        if (!last.isEmpty()) {
            output.add(last);
        }
    }

    private void moveOperatorsFromStackToInputString(int i) {

        while (!stackOperators.getFirst().equals("(")) {
            output.add(stackOperators.pop());
        }
        stackOperators.pop();
    }

    private void pushOperator(Operator operator) {
        while (!stackOperators.isEmpty() && operatorRegistry.searchOperator(stackOperators.getFirst()).getPriority() >= operator.getPriority()) {
            output.add(stackOperators.pop());
        }
        stackOperators.push(operator.getOperator());
    }

}