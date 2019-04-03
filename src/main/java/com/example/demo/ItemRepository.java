package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, String > {
    List<Item> findAll();

}
