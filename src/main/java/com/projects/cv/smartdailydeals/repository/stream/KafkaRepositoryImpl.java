package com.projects.cv.smartdailydeals.repository.stream;

import com.projects.cv.smartdailydeals.model.Deal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;

public class KafkaRepositoryImpl implements KafkaRepository {

    public static final String TOPIC = "expired-deals";

    @Autowired
    KafkaTemplate<String, Deal> kafkaTemplate;

    @Override
    public void publishDeal(Deal deal) {
        kafkaTemplate.send(TOPIC, deal.getId(), deal);
    }

    @Override
    public void publishDeals(List<Deal> deals) {
        for (Deal deal: deals) {
            kafkaTemplate.send(TOPIC, deal.getId(), deal);
        }
    }
}
