package com.projects.cv.smartdailydeals.service;

import com.projects.cv.smartdailydeals.model.Category;
import com.projects.cv.smartdailydeals.model.Item;
import com.projects.cv.smartdailydeals.repository.jpa.CategoryRepository;
import com.projects.cv.smartdailydeals.repository.jpa.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Optional<Category> updateCategory(long id, Category update) {
        Optional<Category>  dbFetchedCategoryOptional = categoryRepository.findById(id);

        if (dbFetchedCategoryOptional.isPresent()) {

            Category dbFetchedCategory = dbFetchedCategoryOptional.get();

            dbFetchedCategory.setParentCategoryId(update.getParentCategoryId());
            dbFetchedCategory.setName(update.getName());
            dbFetchedCategory.setItemsCount(update.getItemsCount());
            dbFetchedCategory.setDealItemsCount(update.getDealItemsCount());
            dbFetchedCategory.setAlwaysOnDeal(update.isAlwaysOnDeal());
            dbFetchedCategory.setDealOffToOnRate(update.getDealOffToOnRate());
            dbFetchedCategory.setTimeBasedDealOn(update.isTimeBasedDealOn());
            dbFetchedCategory.setCeilPercentOfDailyDeals(update.getCeilPercentOfDailyDeals());
            dbFetchedCategory.setHardNumericLimitOfItemsOnDailyDeals(update.getHardNumericLimitOfItemsOnDailyDeals());


            categoryRepository.save(dbFetchedCategory);

            return Optional.of(dbFetchedCategory);

        }

        return Optional.empty();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }
}
