package com.example.hsa12;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CacheRepository extends CrudRepository<CacheData, String> {
}
