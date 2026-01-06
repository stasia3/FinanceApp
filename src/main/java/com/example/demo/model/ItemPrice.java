package com.example.demo.model;

import java.util.Date;

public class ItemPrice {
    private Integer idItemPrice;
    private Date createdAt;
    private Integer idShop;
    private Double price;
    private String TVAType;
    private String isActual;
    private Integer idItem;
    private Integer idPreviousPrice;

    public ItemPrice() {}

    public void setIdItemPrice(Integer idItemPrice) {
        this.idItemPrice = idItemPrice;
    }

    public Integer getIdItemPrice() {
        return idItemPrice;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setIdShop(Integer idShop) {
        this.idShop = idShop;
    }

    public Integer getIdShop() {
        return idShop;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setTVAType(String TVAType) {
        this.TVAType = TVAType;
    }

    public String getTVAType() {
        return TVAType;
    }

    public void setActual(String actual) {
        isActual = actual;
    }

    public String getActual() {
        return isActual;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdPreviousPrice(Integer idPreviousPrice) {
        this.idPreviousPrice = idPreviousPrice;
    }

    public Integer getIdPreviousPrice() {
        return idPreviousPrice;
    }
}
