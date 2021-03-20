package com.projects.cv.smartdailydeals.repository.stream;

import com.projects.cv.smartdailydeals.model.Deal;

import java.util.List;

public interface KafkaRepository {
    void publishDeal(Deal deal);
    void publishDeals(List<Deal> deals);
}
