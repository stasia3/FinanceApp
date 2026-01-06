package com.example.demo.controller;

import com.example.demo.dao.ShopDAO;
import com.example.demo.dto.ShopSelectDTO;
import com.example.demo.model.Shop;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/api/shop")
@CrossOrigin // allows frontend calls
public class ShopController {
    private final ShopDAO dao;

    public ShopController(ShopDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Shop> getALl() {
        return dao.readAll();
    }

    @PostMapping
    public ResponseEntity<?> saveShop(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("country") String country,
            @RequestParam("city") String city,
            @RequestParam("street") String street,
            @RequestParam("isFizic") String isFizic,
            @RequestParam("site") String site,
            @RequestParam(value = "img", required = false)MultipartFile img
            ) throws IOException {
        Shop shop = new Shop();

        if(img != null && !img.isEmpty()) {
            if(!img.getContentType().startsWith("image/")) {
                return ResponseEntity.badRequest().body("Invalid image type");
            }

            String uploadDir = "uploads/shop/";
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) uploadFolder.mkdirs();

            String fileName = img.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            try (var inputStream = img.getInputStream()) {
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }

            shop.setImg(fileName);
        } else {
            shop.setImg("default.png");
        }

        shop.setName(name);
        shop.setDescription(description);
        shop.setCountry(country);
        shop.setCity(city);
        shop.setStreet(street);
        shop.setIsFizic(isFizic);
        shop.setSite(site);
        dao.insertShop(shop);

        return ResponseEntity.ok(shop);
    }

    @GetMapping("/select")
    public List<ShopSelectDTO> getShopsForSelect() {
        return dao.readForSelect();
    }
}
