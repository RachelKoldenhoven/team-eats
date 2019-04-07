package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class ItemController {
    private final ItemRepository repository;

    public ItemController(ItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/api/items", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Item> getItems() {
        return this.repository.findAll();
    }

    @PostMapping(path = "/api/items", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Item createItem(@RequestBody Item item) {
        item.setId(UUID.randomUUID().toString());
        this.repository.save(item);
        return item;
    }
}
