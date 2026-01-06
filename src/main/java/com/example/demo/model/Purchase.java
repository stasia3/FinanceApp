package com.example.demo.model;

import java.util.Date;

public class Purchase {
    private Integer idPurchase;
    private Integer idShop;
    private Date date;
    private Double discountTotal;
    private String paymentType;
    private Double priceTotal;
    private Double TVATotal;

    public Purchase() {}

    public void setIdPurchase(Integer idPurchase) {
        this.idPurchase = idPurchase;
    }

    public Integer getIdPurchase() {
        return idPurchase;
    }

    public void setIdShop(Integer idShop) {
        this.idShop = idShop;
    }

    public Integer getIdShop() {
        return idShop;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDiscountTotal(Double discountTotal) {
        this.discountTotal = discountTotal;
    }

    public Double getDiscountTotal() {
        return discountTotal;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPriceTotal(Double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public Double getPriceTotal() {
        return priceTotal;
    }

    public void setTVATotal(Double TVATotal) {
        this.TVATotal = TVATotal;
    }

    public Double getTVATotal() {
        return TVATotal;
    }
}
