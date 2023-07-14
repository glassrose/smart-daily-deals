package com.projects.cv.smartdailydeals.service;

import com.projects.cv.smartdailydeals.model.Category;
import com.projects.cv.smartdailydeals.model.Item;
import com.projects.cv.smartdailydeals.repository.jpa.CategoryRepository;
import com.projects.cv.smartdailydeals.repository.jpa.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public int addItem(Item item) {
        Optional<Category> category = categoryRepository.findById(item.getCategoryId());
        if (!category.isPresent()) {
            return 1;
        }
        itemRepository.save(item);
        return 0;
    }

    @Override
    public Optional<Item> updateItem(long id, Item update) {

        Optional<Item>  dbFetchedItemOptional = itemRepository.findById(id);

        if (dbFetchedItemOptional.isPresent()) {

            Item dbFetchedItem = dbFetchedItemOptional.get();

            dbFetchedItem.setCost(update.getCost());
            dbFetchedItem.setDealApplied(update.isDealApplied());
            dbFetchedItem.setDescription(update.getDescription());
            dbFetchedItem.setImageUrl(update.getImageUrl());
            dbFetchedItem.setName(update.getName());
            dbFetchedItem.setReviews(update.getReviews());
            dbFetchedItem.setStockKeepingUnit(update.getStockKeepingUnit());
            dbFetchedItem.setUniversalProductCode(update.getUniversalProductCode());

            itemRepository.save(dbFetchedItem);

            return Optional.of(dbFetchedItem);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }
}
