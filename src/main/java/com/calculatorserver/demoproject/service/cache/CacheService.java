package com.calculatorserver.demoproject.service.cache;

import com.calculatorserver.demoproject.dto.CalculateRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CacheService {


    private Integer cacheSize;
    private final LinkedHashMap<CalculateRequest, String> unsafeCacheValues = new LinkedHashMap<>();
    private final Map<CalculateRequest, String> cacheValues = Collections.synchronizedMap(unsafeCacheValues);

    @Value("${cache.sizeCache}")
    public void setSizeCache(Integer sizeCache) {
        this.cacheSize = sizeCache;
    }

    public void addElementInCache(CalculateRequest calculateRequest, String calculate) {
        Iterator<Map.Entry<CalculateRequest, String>> iterator = cacheValues.entrySet().iterator();
        if (cacheValues.size() >= cacheSize) {
            cacheValues.remove(iterator.next().getKey());
        }
        cacheValues.put(calculateRequest, calculate);

    }

    public String findElementInCache(CalculateRequest calculateRequest) {
        return cacheValues.get(calculateRequest);
    }

}
