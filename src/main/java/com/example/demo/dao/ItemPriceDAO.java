package com.example.demo.dao;

import com.example.demo.model.ItemPrice;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ItemPriceDAO {
    private final DataSource dataSource;

    public ItemPriceDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<ItemPrice> readAll() {
        String sql = "SELECT idItemPrice, createdAt, idShop, price, TVAType, isActual, idItem, idPreviousPrice FROM item_price";
        List<ItemPrice> listItemPrices = new ArrayList<>();

        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while(rs.next()) {
                ItemPrice item = new ItemPrice();
                item.setIdItemPrice(rs.getInt("idItemPrice"));
                item.setCreatedAt(rs.getDate("createdAt"));
                item.setIdShop(rs.getInt("idShop"));
                item.setPrice(rs.getDouble("price"));
                item.setTVAType(rs.getString("TVAType"));
                System.out.println("isActual: " + rs.getString("isActual"));
                item.setActual(rs.getString("isActual"));
                item.setIdItem(rs.getInt("idItem"));
                item.setIdPreviousPrice(rs.getInt("idPreviousPrice"));
                listItemPrices.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listItemPrices;
    }

    public void insertItemPrice(ItemPrice itemPrice) {
        String sql = "INSERT INTO item_price(createdAt, idShop, price, TVAType, isActual, idItem, idPreviousPrice) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, new java.sql.Date(itemPrice.getCreatedAt().getTime()));
            stmt.setInt(2, itemPrice.getIdShop());
            stmt.setDouble(3, itemPrice.getPrice());
            stmt.setString(4, itemPrice.getTVAType());
            stmt.setString(5, itemPrice.getActual());
            stmt.setInt(6, itemPrice.getIdItem());
            stmt.setInt(7, itemPrice.getIdPreviousPrice());

            stmt.executeUpdate();
            System.out.println("ItemPrice inserted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
