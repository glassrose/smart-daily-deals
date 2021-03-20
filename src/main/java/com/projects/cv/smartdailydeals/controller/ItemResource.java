package com.projects.cv.smartdailydeals.controller;

import com.projects.cv.smartdailydeals.model.Item;
import com.projects.cv.smartdailydeals.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/item")
public class ItemResource {
    @Autowired
    ItemService itemService;

    @GetMapping("/{id}")
    public Item getItem(@PathVariable long id) {
        Optional<Item> itemOptional = itemService.getItemById(id);
        if(itemOptional.isPresent()) {
            return itemOptional.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No item found with the provided 'id' value:"+id);
    }

    @PostMapping("/add")
    public void add(@RequestBody final Item item) {
        itemService.addItem(item);
    }

    @PutMapping("/update/{id}")
    public Item update(@PathVariable("id") final long id, @RequestBody Item item) {
        itemService.updateItem(id, item);
        return getItem(id);
    }
}
