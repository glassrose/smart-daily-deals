package com.projects.cv.smartdailydeals.repository.jpa;

import com.projects.cv.smartdailydeals.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
