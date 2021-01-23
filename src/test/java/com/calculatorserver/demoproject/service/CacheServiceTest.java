package com.calculatorserver.demoproject.service;

import com.calculatorserver.demoproject.dto.CalculateRequest;
import com.calculatorserver.demoproject.service.cache.CacheService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CacheServiceTest {

    @Test
    void addElementInCache() {
        CacheService cacheService = new CacheService();
        cacheService.setSizeCache(3);
        CalculateRequest calculateRequest1 = new CalculateRequest("8-2", 1);
        CalculateRequest calculateRequest2 = new CalculateRequest("8-2", 2);
        CalculateRequest calculateRequest3 = new CalculateRequest("11+2", 3);
        CalculateRequest calculateRequest4 = new CalculateRequest("11+3", 3);

        cacheService.addElementInCache(calculateRequest1, "6");
        cacheService.addElementInCache(calculateRequest2, "6");
        cacheService.addElementInCache(calculateRequest3, "13");
        cacheService.addElementInCache(calculateRequest4, "14");

        Assertions.assertEquals("6", cacheService.findElementInCache(new CalculateRequest("8-2", 2)));
    }
}