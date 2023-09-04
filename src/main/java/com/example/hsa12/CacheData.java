package com.example.hsa12;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@NoArgsConstructor
@Getter
@Setter
public class CacheData implements Serializable {
    private String id;
    private long expiry;
    private long delta;
}
