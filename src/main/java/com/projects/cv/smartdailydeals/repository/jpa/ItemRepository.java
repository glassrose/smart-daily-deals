package com.projects.cv.smartdailydeals.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projects.cv.smartdailydeals.model.Item;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT DISTINCT i.categoryId FROM Item i")
    List<Long> findDistinctCategoryIds ();

//    @Query("SELECT i FROM Item i where i.categoryId = ?1 ORDER BY rand()") // randomization is anyhow done at service level
    @Query("SELECT i FROM Item i where i.categoryId = ?1")
    List<Item> findItemByCategoryIds (long categoryId);
}
