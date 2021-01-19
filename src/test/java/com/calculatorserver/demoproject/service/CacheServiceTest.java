package com.calculatorserver.demoproject.service;

import com.calculatorserver.demoproject.entity.Cache;
import com.calculatorserver.demoproject.service.cache.CacheService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CacheServiceTest {

    @Test
    void addElementInCache() {
        CacheService cacheService = new CacheService();
        cacheService.setSizeCache(2);
        cacheService.addElementInCache(new Cache("5+5", 5));
        cacheService.addElementInCache(new Cache("8-2", 1));
        cacheService.addElementInCache(new Cache("100+7", 8));
        Cache cache = new Cache("11+2", 3);
        cacheService.addElementInCache(cache);
        Assertions.assertEquals(cache, cacheService.findElementInCache(String.format("%s#%d", "11+2", 3)));
    }
}