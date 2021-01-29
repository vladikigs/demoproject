package com.calculatorserver.demoproject.service.calculator.service;

import com.calculatorserver.demoproject.service.ParserFactory;
import com.calculatorserver.demoproject.service.calculator.entity.Operator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Calculator {


    private final OperatorRegistry operatorRegistry;

    private final ParserFactory parserFactory;


    public Calculator(OperatorRegistry operatorRegistry, ParserFactory parserFactory) {
        this.operatorRegistry = operatorRegistry;
        this.parserFactory = parserFactory;
    }

    public String calculate(String example, Integer precision) {
        ReversePolishNotationParser reversePolishNotationParser = parserFactory.getReversePolishNotationParser();
        List<String> reversPolishNotation = new ArrayList<>(reversePolishNotationParser.parse(example));
        return calculatePolishNotation(reversPolishNotation, precision);
    }

    private String calculatePolishNotation(List<String> reversPolishNotation, Integer precision) {
        for (int i = 0; i < reversPolishNotation.size(); i++) {
            Operator operator = operatorRegistry.searchOperator(reversPolishNotation.get(i));

            if (reversPolishNotation.get(i).equals("(")) {
                reversPolishNotation.remove(i);
                ArrayList<String> newCalc = new ArrayList<>();
                while (!reversPolishNotation.get(i).equals(")")) {
                    newCalc.add(reversPolishNotation.get(i));
                    reversPolishNotation.remove(i);
                }
                reversPolishNotation.set(i, calculatePolishNotation(newCalc, precision));
                i=0;
            }

            if (operator != null) {
                if (i >= 2) {
                    reversPolishNotation.set(i - 2, operatorRegistry.calculationOperatorLogic(
                            reversPolishNotation.get(i - 2),
                            reversPolishNotation.get(i - 1),
                            operator,
                            precision
                    ));
                    reversPolishNotation.remove(i);
                    reversPolishNotation.remove(i - 1);
                    i -= 2;
                } else {
                    if (reversPolishNotation.get(i).equals("-")) {
                        reversPolishNotation.set(i - 1, operatorRegistry.calculationOperatorLogic(
                                "-1",
                                reversPolishNotation.get(i - 1),
                                operatorRegistry.getOperators()[4],
                                precision
                        ));
                        reversPolishNotation.remove(i);
                    }
                }
            }
        }
        return reversPolishNotation.get(0);
    }
}