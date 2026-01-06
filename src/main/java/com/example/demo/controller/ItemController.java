package com.example.demo.controller;

import com.example.demo.dao.ItemDAO;
import com.example.demo.model.Item;
import org.apache.logging.log4j.message.ExitMessage;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/item")
@CrossOrigin // allows frontend calls
public class ItemController {

    private final ItemDAO dao;

    public ItemController(ItemDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Item> getAll() {
        return dao.readAll();
    }

    @PostMapping
    public ResponseEntity<?> saveItem(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("type") String type,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) throws IOException {
        // Save item in DB
        Item item = new Item();

        if(image != null && !image.isEmpty()){
            if (!image.getContentType().startsWith("image/")) {
                return ResponseEntity.badRequest().body("Invalid image type");
            }

            String uploadDir = "uploads/";
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) uploadFolder.mkdirs();

            String fileName = image.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            try (var inputStream = image.getInputStream()) {
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }

            item.setImage(fileName);
        } else {
            item.setImage("default.png");
        }

        item.setName(name);
        item.setDescription(description);
        item.setType(type);
        dao.insertItem(item);

        return ResponseEntity.ok(item);
    }

}
