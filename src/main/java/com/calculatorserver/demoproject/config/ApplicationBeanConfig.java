package com.calculatorserver.demoproject.config;

import com.calculatorserver.demoproject.dto.CalculationDto;
import com.calculatorserver.demoproject.service.calculator.service.Calculator;
import com.calculatorserver.demoproject.service.calculator.service.OperatorRegistry;
import com.calculatorserver.demoproject.service.calculator.service.ReversePolishNotation;
import com.calculatorserver.demoproject.service.calculator.service.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfig {

//    @Bean
//    public CacheService cacheService() {
//        return new CacheService();
//    }

    @Bean
    public OperatorRegistry operatorRegistry() {
        return new OperatorRegistry();
    }

    @Bean
    public Validator validator(OperatorRegistry operatorRegistry) {
        return new Validator(operatorRegistry);
    }

    @Bean
    public ReversePolishNotation reversePolishNotation(Validator validator, OperatorRegistry operatorRegistry) {
        return new ReversePolishNotation(validator, operatorRegistry);
    }

    @Bean
    public Calculator calculator(@Autowired OperatorRegistry operatorRegistry,@Autowired ReversePolishNotation reversePolishNotation) {
        return new Calculator(operatorRegistry, reversePolishNotation);
    }

    @Bean
    public CalculationDto calculationDto() {
        return new CalculationDto();
    }

}
