package com.projects.cv.smartdailydeals.repository.cache;

import com.projects.cv.smartdailydeals.model.Deal;
import com.projects.cv.smartdailydeals.model.DealItem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface DealsRepository {
    void save(Deal deal);
    void saveAll(List<Deal> deals);
    Map<String, Deal> findAll();
    Optional<Deal> findById(String id);

    /**
     *  Extras!
     */
    void update(Deal deal);
    void delete(String id);
}
