package com.projects.cv.smartdailydeals.service;

import com.projects.cv.smartdailydeals.model.Item;

import java.util.Optional;

public interface ItemService {
    void addItem(Item item);
    Optional<Item> updateItem(long id, Item update);
    Optional<Item> getItemById(Long id);
}
