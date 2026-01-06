package com.example.demo.controller;

import com.example.demo.dao.ItemPriceDAO;
import com.example.demo.model.ItemPrice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itemprice")
@CrossOrigin
public class ItemPriceController {
    private final ItemPriceDAO dao;

    public ItemPriceController(ItemPriceDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<ItemPrice> getAll() {
        return dao.readAll();
    }

    @PostMapping
    public ItemPrice create(@RequestBody ItemPrice itemPrice) {
        dao.insertItemPrice(itemPrice);
        return itemPrice;
    }

}
