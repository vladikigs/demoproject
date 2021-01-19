package com.calculatorserver.demoproject.service.calculator.service;


import com.calculatorserver.demoproject.service.calculator.entity.Operator;

public class Validator {

    private OperatorRegistry operatorRegistry;

    public Validator(OperatorRegistry operatorRegistry) {
        this.operatorRegistry = operatorRegistry;
    }


    public void validate(String inputString) {

        if (inputString.trim().isEmpty()) {
            throw new IllegalArgumentException("Пустая строка");
        }

        int countNumber = 0;
        int countOperator = 0;
        int countOpenParenthesis = 0;
        int countCloseParenthesis = 0;
        char previousSymbol = ' ';
        char nextSymbol = ' ';

        Operator lastSymbol = operatorRegistry.searchOperator(String.valueOf(inputString.charAt(inputString.length() - 1)));
        if (lastSymbol != null) {
            if (!lastSymbol.getOperator().equals(")")) {
                throw new IllegalArgumentException("В конце выражения не может быть оператор");
            }
        }
        Operator firstSymbol = operatorRegistry.searchOperator(String.valueOf(inputString.charAt(0)));
        if (firstSymbol != null) {
            if (!firstSymbol.getOperator().equals("(")) {
                throw new IllegalArgumentException("Выражение не может начинаться с оператора");
            }

        }


        for (int i = 0; i < inputString.length(); i++) {

            char currentSymbol = inputString.charAt(i);

            if (currentSymbol == ' ') continue;

            if (i <= inputString.length() - 2) {
                nextSymbol = inputString.charAt(i + 1);
            } else {
                nextSymbol = ' ';
            }

            if (previousSymbol == '(' && currentSymbol == ')') {
                throw new IllegalArgumentException("В скобках нет выражения");
            }

            if (currentSymbol == '(') {
                previousSymbol = inputString.charAt(i);
                countOpenParenthesis++;
                continue;
            }

            if (currentSymbol == ')') {
                previousSymbol = inputString.charAt(i);
                countCloseParenthesis++;
                continue;
            }



            if (previousSymbol == '(' && currentSymbol == '-') {
                continue;
            }


            if (Character.isDigit(currentSymbol) && (!Character.isDigit(nextSymbol) || nextSymbol == ' ')) {
                if (nextSymbol != '.') countNumber++;

            }

            if (Character.isDigit(currentSymbol)) {
                previousSymbol = inputString.charAt(i);
                continue;
            }

            if (currentSymbol == '.') {
                if (Character.isDigit(previousSymbol) && Character.isDigit(nextSymbol)) {
                    continue;
                } else {
                    throw new IllegalArgumentException("Некорректное расположение точки");
                }
            }

            Operator currentOperator = operatorRegistry.searchOperator(String.valueOf(currentSymbol));

            if (currentOperator != null && !currentOperator.getOperator().equals("(") && !currentOperator.getOperator().equals(")")) {
                countOperator++;
            } else if (currentOperator == null) {
                throw new IllegalArgumentException("Неизветный оператор " + inputString.charAt(i));
            }
        }
        if (countNumber - 1 != countOperator) {
            throw new IllegalArgumentException("Операторы или числа не могут быть написаны два раза подряд");
        }

        if (countOpenParenthesis != countCloseParenthesis) {
            throw new IllegalArgumentException("Количество открытых и закрытых скобок не совпадает");
        }
    }

}
