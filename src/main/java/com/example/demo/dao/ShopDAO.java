package com.example.demo.dao;

import com.example.demo.dto.ShopSelectDTO;
import com.example.demo.model.Shop;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ShopDAO {
    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    public ShopDAO(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Shop> readAll() {
        String sql = "SELECT idShop, name, description, country, city, street, isFizic, site, img FROM shop";
        List<Shop> listShops = new ArrayList<>();

        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Shop shop = new Shop();
                shop.setIdShop(rs.getInt("idShop"));
                shop.setName(rs.getString("name"));
                shop.setDescription(rs.getString("description"));
                shop.setCountry(rs.getString("country"));
                shop.setCity(rs.getString("city"));
                shop.setStreet(rs.getString("street"));
                shop.setIsFizic(rs.getString("isFizic"));
                shop.setSite(rs.getString("site"));
                shop.setImg(rs.getString("img"));
                listShops.add(shop);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listShops;
    }

    public void insertShop(Shop shop) {
        String sql = "INSERT INTO shop(name, description, country, city, street, isFizic, site, img) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, shop.getName());
            stmt.setString(2, shop.getDescription());
            stmt.setString(3, shop.getCountry());
            stmt.setString(4, shop.getCity());
            stmt.setString(5, shop.getStreet());
            stmt.setString(6, shop.getIsFizic());
            stmt.setString(7, shop.getSite());
            stmt.setString(8, shop.getImg());

            stmt.executeUpdate();
            System.out.println("Shop inserted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ShopSelectDTO> readForSelect() {
        String sql = """
                    SELECT idShop AS id,
                        CONCAT(name, ' - ', description) AS label
                    FROM shop
                    ORDER BY name
                """;

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) ->
                        new ShopSelectDTO(
                                rs.getInt("id"),
                                rs.getString("label")
                        )
        );

    }
}
