package com.calculatorserver.demoproject.service.calculator.service;

import com.calculatorserver.demoproject.service.calculator.entity.Operator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ROUND_DOWN;

public class Calculator {

    private OperatorRegistry operatorRegistry;
    private ReversePolishNotation reversePolishNotation;

    public Calculator(OperatorRegistry operatorRegistry, ReversePolishNotation reversePolishNotation) {
        this.operatorRegistry = operatorRegistry;
        this.reversePolishNotation = reversePolishNotation;
    }

    public String calculate(String example, Integer precision) {
        List<String> reversPolishNotation = new ArrayList<>(reversePolishNotation.parse(example));

        calculatePolishNotation(reversPolishNotation);

        return new BigDecimal(reversPolishNotation.get(0)).setScale(precision, ROUND_DOWN).toString();
    }

    private String calculatePolishNotation(List<String> reversPolishNotation) {
        for (int i = 0; i < reversPolishNotation.size(); i++) {
            Operator operator = operatorRegistry.searchOperator(reversPolishNotation.get(i));

            if (reversPolishNotation.get(i).equals("(")) {
                reversPolishNotation.remove(i);
                ArrayList<String> newCalc = new ArrayList<>();
                while (!reversPolishNotation.get(i).equals(")")) {
                    newCalc.add(reversPolishNotation.get(i));
                    reversPolishNotation.remove(i);
                }
                reversPolishNotation.set(i, calculatePolishNotation(newCalc));
                i=0;
            }

            if (operator != null) {
                if (i >= 2) {
                    reversPolishNotation.set(i - 2, operatorRegistry.calculationOperatorLogic(
                            reversPolishNotation.get(i - 2),
                            reversPolishNotation.get(i - 1),
                            operator
                    ));
                    reversPolishNotation.remove(i);
                    reversPolishNotation.remove(i - 1);
                    i -= 2;
                } else {
                    if (reversPolishNotation.get(i).equals("-")) {
                        reversPolishNotation.set(i - 1, operatorRegistry.calculationOperatorLogic(
                                "-1",
                                reversPolishNotation.get(i - 1),
                                operatorRegistry.getOperators()[4]
                        ));
                        reversPolishNotation.remove(i);
                    }
                }
            }
        }
        return reversPolishNotation.get(0);
    }
}