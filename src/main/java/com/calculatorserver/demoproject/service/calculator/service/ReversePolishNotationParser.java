package com.calculatorserver.demoproject.service.calculator.service;

import com.calculatorserver.demoproject.service.calculator.entity.Operator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ReversePolishNotationParser {

    private final Deque<String> stackOperators = new ArrayDeque<>();

    private LinkedList<String> output = new LinkedList<>();
    private final Validator validator;
    private final OperatorRegistry operatorRegistry;

    public ReversePolishNotationParser(Validator validator, OperatorRegistry operatorRegistry) {
        this.validator = validator;
        this.operatorRegistry = operatorRegistry;
    }


    public List<String> parse(String inputString) {
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
                    moveOperatorsFromStackToInputString();
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

    private void moveOperatorsFromStackToInputString() {
        while (!stackOperators.getFirst().equals("(")) {
            output.add(stackOperators.pop());
        }
        stackOperators.pop();
    }

    private void pushOperator(Operator operator) {
        while (!stackOperators.isEmpty() && operatorRegistry.searchOperator(stackOperators.getFirst()).getPriority() >= operator.getPriority()) {
            output.add(stackOperators.pop());
        }
        stackOperators.push(operator.getMathOperator());
    }

}