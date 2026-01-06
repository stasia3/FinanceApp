package com.example.demo.model;

public class PurchaseItem {
    private Integer idPurchaseItem;
    private Double quantity;
    private String quantityUnit;
    private Integer discountProcent;
    private Integer discountPrice;
    private Double total;
    private Integer idItemPrice;
    private Integer idPurchase;
    private String currency;

    public PurchaseItem() {}

    public void setIdPurchaseItem(Integer idPurchaseItem) {
        this.idPurchaseItem = idPurchaseItem;
    }

    public Integer getIdPurchaseItem() {
        return idPurchaseItem;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public void setDiscountProcent(Integer discountProcent) {
        this.discountProcent = discountProcent;
    }

    public Integer getDiscountProcent() {
        return discountProcent;
    }

    public void setDiscountPrice(Integer discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Integer getDiscountPrice() {
        return discountPrice;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotal() {
        return total;
    }

    public void setIdItemPrice(Integer idItemPrice) {
        this.idItemPrice = idItemPrice;
    }

    public Integer getIdItemPrice() {
        return idItemPrice;
    }

    public void setIdPurchase(Integer idPurchase) {
        this.idPurchase = idPurchase;
    }

    public Integer getIdPurchase() {
        return idPurchase;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }
}
