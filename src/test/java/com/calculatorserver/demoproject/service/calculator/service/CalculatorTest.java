package com.calculatorserver.demoproject.service.calculator.service;

import com.calculatorserver.demoproject.service.calculator.entity.Operator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalculatorTest {

    @Autowired
    Calculator calculator;

    @Test
    void calculate() {
        String calculate = calculator.calculate("0.385+0.0001", 1);
        calculate = calculator.calculate("0.385+0.0201", 2);
        System.out.println(calculate);
    }

}