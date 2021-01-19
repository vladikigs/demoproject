package com.calculatorserver.demoproject.controller;

import com.calculatorserver.demoproject.dto.CalculateRequest;
import com.calculatorserver.demoproject.dto.CalculationDto;
import com.calculatorserver.demoproject.entity.Cache;
import com.calculatorserver.demoproject.entity.Calculation;
import com.calculatorserver.demoproject.entity.User;
import com.calculatorserver.demoproject.repository.UserRepository;
import com.calculatorserver.demoproject.service.cache.CacheService;
import com.calculatorserver.demoproject.service.SimpleUserExpressionsService;
import com.calculatorserver.demoproject.service.calculator.service.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CalculatorController {

    @Autowired
    Calculator calculator;

    @Autowired
    CacheService cacheService;

    @Autowired
    SimpleUserExpressionsService simpleUserExpressionsService;

    @Autowired
    CalculationDto calculationDto;


    @PostMapping("/calculator")
    @ResponseBody
    public String serviceCalculator(@RequestBody CalculateRequest calculateRequest, Authentication authentication) {
        User user = simpleUserExpressionsService.getUserByLogin(authentication.getName());
        Cache elementInCache = cacheService.findElementInCache(calculateRequest.getExpression()+"#"+calculateRequest.getPrecision());

        if (elementInCache != null) {
            simpleUserExpressionsService.addCalculation(calculateRequest, false, user);
            return elementInCache.getResultOfExpression();
        } else {
            String calculate = calculator.calculate(calculateRequest.getExpression(), calculateRequest.getPrecision());
            simpleUserExpressionsService.addCalculation(calculateRequest, true, user);
            Cache cache = new Cache(calculateRequest.getExpression(),
                    calculateRequest.getPrecision());
            cache.setResultOfExpression(calculate);
            cacheService.addElementInCache(cache);

            return calculate;
        }

    }

    @GetMapping("/getCalculations/{login}")
    @ResponseBody
    public List<CalculationDto> getUserCalculations(@PathVariable(name = "login") String login) {
        User user = simpleUserExpressionsService.getUserByLogin(login);
        List<Calculation> userExpressions = simpleUserExpressionsService.getUserExpressions(user);
        return calculationDto.convertListToDto(userExpressions);
    }


}
