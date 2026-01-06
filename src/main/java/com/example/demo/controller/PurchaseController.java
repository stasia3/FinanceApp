package com.example.demo.controller;

import com.example.demo.dao.PurchaseDAO;
import com.example.demo.model.Purchase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase")
@CrossOrigin
public class PurchaseController {
    private final PurchaseDAO dao;

    public PurchaseController(PurchaseDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Purchase> getAll() {
        return dao.readAll();
    }

    @PostMapping
    public Purchase create(@RequestBody Purchase purchase) {
        dao.insertPurchase(purchase);
        return purchase;
    }
}
