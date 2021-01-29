package com.calculatorserver.demoproject.service.cache;

import com.calculatorserver.demoproject.dto.CalculateRequest;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@ThreadSafe
public class CacheService {


    private Integer cacheSize;
    @GuardedBy("this")
    private final LinkedHashMap<CalculateRequest, String> cacheValues = new LinkedHashMap<>();


    @Value("${cache.sizeCache}")
    public void setSizeCache(Integer sizeCache) {
        this.cacheSize = sizeCache;
    }

    public synchronized void addElementInCache(CalculateRequest calculateRequest, String calculate) {
            Iterator<Map.Entry<CalculateRequest, String>> iterator = cacheValues.entrySet().iterator();
            if (cacheValues.size() >= cacheSize) {
                cacheValues.remove(iterator.next().getKey());
            }
            cacheValues.put(calculateRequest, calculate);
    }

    public synchronized String findElementInCache(CalculateRequest calculateRequest) {
        return cacheValues.get(calculateRequest);
    }

}
