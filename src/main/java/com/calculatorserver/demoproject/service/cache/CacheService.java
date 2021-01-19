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
    private final Set<Cache> cacheValues = new LinkedHashSet<>();

    @Value("${cache.sizeCache}")
    public void setSizeCache(Integer sizeCache) {
        this.sizeCache = sizeCache;
    }

    public void addElementInCache(Cache cache) {
        if (cacheValues.size() >= sizeCache) {
            cacheValues.remove(cacheValues.iterator().next());
        }
        cacheValues.add(cache);

    }

    public Cache findElementInCache(String expression) {
        Set<Cache> objects = cacheValues.stream().filter(value ->
                String.format("%s#%d", value.getExpression(), value.getPrecision()).equals(expression)).collect(Collectors.toSet());
        return objects.size() == 1?objects.iterator().next():null;
    }

}
