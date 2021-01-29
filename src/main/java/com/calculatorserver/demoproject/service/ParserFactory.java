package com.calculatorserver.demoproject.service;

import com.calculatorserver.demoproject.service.calculator.service.OperatorRegistry;
import com.calculatorserver.demoproject.service.calculator.service.ReversePolishNotationParser;
import com.calculatorserver.demoproject.service.calculator.service.Validator;
import org.springframework.stereotype.Component;

@Component
public class ParserFactory {

    private final OperatorRegistry operatorRegistry;
    private final Validator validator;

    public ParserFactory(OperatorRegistry operatorRegistry, Validator validator) {
        this.operatorRegistry = operatorRegistry;
        this.validator = validator;
    }

    public ReversePolishNotationParser getReversePolishNotationParser() {
        return new ReversePolishNotationParser(validator, operatorRegistry);
    }



}
