package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class ItemController {
    private List<Item> items = new ArrayList<>();

    @GetMapping(path = "/api/items", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Item> getItems() {
        return items;
    }

    @PostMapping(path = "/api/items", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Item createItem(@RequestBody Item item) {
        item.setId(UUID.randomUUID().toString());
        items.add(item);
        return item;
    }
}
