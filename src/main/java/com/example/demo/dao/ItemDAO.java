package com.example.demo.dao;


import com.example.demo.model.Item;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemDAO {
    private final DataSource dataSource;

    public ItemDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Item> readAll() {
        String sql = "SELECT idItem, name, description, type, image FROM item";
        List<Item> listItems = new ArrayList<Item>();

        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Item item = new Item();
                item.setIdItem(rs.getInt("idItem"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setType(rs.getString("type"));
                item.setImage(rs.getString("image"));
                listItems.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listItems;
    }

    public void insertItem(String name, String description, String type, String image) {
        String sql = "INSERT INTO item(name, description, type, image) VALUES (?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setString(3, type);
            stmt.setString(4, image);

            stmt.executeUpdate();
            System.out.println("Item inserted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertItem(Item item) {
        String sql = "INSERT INTO item(name, description, type, image) VALUES (?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getName());
            stmt.setString(2, item.getDescription());
            stmt.setString(3, item.getType());
            stmt.setString(4, item.getImage());

            stmt.executeUpdate();
            System.out.println("Item inserted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
