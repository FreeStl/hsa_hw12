package com.example.hsa12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

import static java.lang.Math.log;
import static java.util.concurrent.ThreadLocalRandom.current;

@Service
public class CacheService {
    @Autowired
    CacheRepository cacheRepository;

    public String xFetch(String id, long ttl, int beta) throws InterruptedException {
        var res = cacheRepository.findById(id);
        if (res.isEmpty() || check(res.get(), beta)) {
            var data = recomputeValue(id, ttl);
            return cacheRepository.save(data).getId();
        }
        return res.get().getId();
    }

    private CacheData recomputeValue(String id, long ttl) throws InterruptedException {
        var start = Instant.now();
        Thread.sleep(1000);
        var data = new CacheData();
        data.setId(id);
        data.setDelta(Instant.now().toEpochMilli() - start.toEpochMilli());
        data.setExpiry(ttl);
        return data;
    }

    private boolean check(CacheData data, int beta) {
        return data.getDelta() * beta * log(current().nextDouble(0, 1)) >= data.getExpiry();
    }
}
