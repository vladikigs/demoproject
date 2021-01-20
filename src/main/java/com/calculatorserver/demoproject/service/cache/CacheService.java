package com.calculatorserver.demoproject.service.cache;

import com.calculatorserver.demoproject.entity.Cache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CacheService {


    private Integer sizeCache;
    private final LinkedHashMap<String, Cache> cacheValues = new LinkedHashMap<>();

    @Value("${cache.sizeCache}")
    public void setSizeCache(Integer sizeCache) {
        this.sizeCache = sizeCache;
    }

    public void addElementInCache(Cache cache) {
        Iterator<Map.Entry<String, Cache>> iterator = cacheValues.entrySet().iterator();
        if (cacheValues.size() >= sizeCache) {
            cacheValues.remove(iterator.next().getKey());
        }
        cacheValues.put(cache.getExpression()+"#"+cache.getPrecision(), cache);

    }

    public Cache findElementInCache(String expression) {
        return cacheValues.get(expression);
    }

}
