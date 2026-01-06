package com.example.demo.dao;

import com.example.demo.model.Purchase;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PurchaseDAO {
    private final DataSource dataSource;

    public PurchaseDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Purchase> readAll() {
        String sql = "SELECT idPurchase, idShop, date, discountTotal, paymentType, priceTotal, TVATotal FROM purchase";
        List<Purchase> listPurchase = new ArrayList<>();

        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

            while(rs.next()) {
                Purchase purchase = new Purchase();
                purchase.setIdPurchase(rs.getInt("idPurchase"));
                purchase.setIdShop(rs.getInt("idShop"));
                purchase.setDate(new Date(rs.getDate("date").getTime()));
                purchase.setDiscountTotal(rs.getDouble("discountTotal"));
                purchase.setPaymentType(rs.getString("paymentType"));
                purchase.setPriceTotal(rs.getDouble("priceTotal"));
                purchase.setTVATotal(rs.getDouble("TVATotal"));
                listPurchase.add(purchase);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return listPurchase;
    }

    public void insertPurchase(Purchase purchase) {
        String sql = "INSERT INTO purchase(idShop, date, discountTotal, paymentType, priceTotal, TVATotal) VALUES(?, ?, ?, ?, ?, ?)";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, purchase.getIdShop());
            stmt.setDate(2, new java.sql.Date(purchase.getDate().getTime()));
            stmt.setDouble(3, purchase.getDiscountTotal());
            stmt.setString(4, purchase.getPaymentType());
            stmt.setDouble(5, purchase.getPriceTotal());
            stmt.setDouble(6, purchase.getTVATotal());

            stmt.executeUpdate();
            System.out.println("Purchase inserted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
