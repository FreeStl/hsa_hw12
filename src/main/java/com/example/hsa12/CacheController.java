package com.example.hsa12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api")
public class CacheController {
    @Autowired
    CacheService cacheService;

    @GetMapping("/{id}")
    String getId(@PathVariable("id") String id) throws InterruptedException {
        return cacheService.xFetch(id, Instant.now().plusSeconds(4).toEpochMilli(), 1);
    }
}
