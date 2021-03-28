package com.projects.cv.smartdailydeals.repository.cache;

import com.projects.cv.smartdailydeals.model.Deal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DealsRepositoryImpl implements DealsRepository {

    private RedisTemplate<String, Deal> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public DealsRepositoryImpl(RedisTemplate<String, Deal> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(Deal deal) {
        hashOperations.put("DEAL", deal.getId(), deal);
    }

    @Override
    public void saveAll(List<Deal> deals) {
        Map<String, Deal> dealsToSave = new HashMap<>();
        deals.parallelStream().forEach(deal->dealsToSave.put(deal.getId(), deal));
        deals.parallelStream()
                .forEach(deal->hashOperations.putAll("DEAL", dealsToSave));
    }

    @Override
    public Map<String, Deal> findAll() {
        return hashOperations.entries("DEAL");
    }

    @Override
    public Optional<Deal> findById(String id) {
        return Optional.ofNullable((Deal)hashOperations.get("DEAL", id));
    }

    @Override
    public void update(Deal deal) {
        save(deal);
    }

    @Override
    public void delete(String id) {
        hashOperations.delete("DEAL", id);
    }
}
