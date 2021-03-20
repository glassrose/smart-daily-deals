package com.projects.cv.smartdailydeals.repository.jpa;

import com.projects.cv.smartdailydeals.model.DealItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DealItemRepository  extends JpaRepository<DealItem, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM DealItem di LIMIT ?1")
    List<DealItem> getFirstNDealItems (int limit);

}
