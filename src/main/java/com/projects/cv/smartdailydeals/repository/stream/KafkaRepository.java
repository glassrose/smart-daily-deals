package com.projects.cv.smartdailydeals.repository.stream;

import com.projects.cv.smartdailydeals.model.Deal;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KafkaRepository {
    void publishDeal(Deal deal);
    void publishDeals(List<Deal> deals);
}
