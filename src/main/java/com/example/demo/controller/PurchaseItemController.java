package com.example.demo.controller;

import com.example.demo.dao.PurchaseDAO;
import com.example.demo.dao.PurchaseItemDAO;
import com.example.demo.model.Purchase;
import com.example.demo.model.PurchaseItem;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchaseitem")
@CrossOrigin
public class PurchaseItemController {
    private final PurchaseItemDAO dao;

    public PurchaseItemController(PurchaseItemDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<PurchaseItem> getAll() {
        return dao.readAll();
    }

    @PostMapping
    public PurchaseItem create(@RequestBody PurchaseItem purchaseItem) {
        dao.insertPurchase(purchaseItem);
        return purchaseItem;
    }
}
