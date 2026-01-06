package com.example.demo.dao;

import com.example.demo.model.PurchaseItem;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PurchaseItemDAO {
    private final DataSource dataSource;

    public PurchaseItemDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<PurchaseItem> readAll() {
        String sql = "SELECT idPurchaseItem, quantity, quantityUnit, discountProcent, discountPrice, total, idItemPrice, idPurchase, currency FROM purchase_item";
        List<PurchaseItem> listPurchaseItem = new ArrayList<>();

        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

            while(rs.next()) {
                PurchaseItem purchase = new PurchaseItem();
                purchase.setIdPurchaseItem(rs.getInt("idPurchaseItem"));
                purchase.setQuantity(rs.getDouble("quantity"));
                purchase.setQuantityUnit(rs.getString("quantityUnit"));
                purchase.setDiscountProcent(rs.getInt("discountProcent"));
                purchase.setDiscountPrice(rs.getInt("discountPrice"));
                purchase.setTotal(rs.getDouble("total"));
                purchase.setIdItemPrice(rs.getInt("idItemPrice"));
                purchase.setIdPurchase(rs.getInt("idPurchase"));
                purchase.setCurrency(rs.getString("currency"));
                listPurchaseItem.add(purchase);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return listPurchaseItem;
    }

    public void insertPurchase(PurchaseItem purchaseItem) {
        String sql = "INSERT INTO purchase_item(quantity, quantityUnit, discountProcent, discountPrice, total, idItemPrice, idPurchase, currency) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, purchaseItem.getQuantity());
            stmt.setString(2, purchaseItem.getQuantityUnit());
            stmt.setInt(3, purchaseItem.getDiscountProcent());
            stmt.setInt(4, purchaseItem.getDiscountPrice());
            stmt.setDouble(5, purchaseItem.getTotal());
            stmt.setInt(6, purchaseItem.getIdItemPrice());
            stmt.setInt(7, purchaseItem.getIdPurchase());
            stmt.setString(8, purchaseItem.getCurrency());

            stmt.executeUpdate();
            System.out.println("Purchase inserted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
