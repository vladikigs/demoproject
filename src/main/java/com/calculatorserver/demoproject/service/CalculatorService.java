package com.calculatorserver.demoproject.service;

import com.calculatorserver.demoproject.dto.CalculateRequest;
import com.calculatorserver.demoproject.entity.User;
import com.calculatorserver.demoproject.service.cache.CacheService;
import com.calculatorserver.demoproject.service.calculator.service.Calculator;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    private final CacheService cacheService;
    private final CalculationsHistoryService calculationsHistoryService;
    private final Calculator calculator;

    public CalculatorService(CacheService cacheService, CalculationsHistoryService calculationsHistoryService, Calculator calculator) {
        this.cacheService = cacheService;
        this.calculationsHistoryService = calculationsHistoryService;
        this.calculator = calculator;
    }


    public String calculateExpression(User user, CalculateRequest calculateRequest) {
        String elementInCache = cacheService.findElementInCache(calculateRequest);

        if (elementInCache != null) {
            calculationsHistoryService.saveUserRequest(calculateRequest, false, user);
            return elementInCache;
        } else {
            String calculate = calculator.calculate(calculateRequest.getExpression(), calculateRequest.getPrecision());

            calculationsHistoryService.saveUserRequest(calculateRequest, true, user);


            cacheService.addElementInCache(calculateRequest, calculate);
            return calculate;
        }
    }

}
