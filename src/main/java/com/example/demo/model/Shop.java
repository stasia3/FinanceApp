package com.example.demo.model;

public class Shop {
    private Integer idShop;
    private String name;
    private String country;
    private String city;
    private String street;
    private String isFizic;
    private String site;
    private String img;
    private String description;

    public Shop() {}

    public void setIdShop(Integer idShop) {
        this.idShop = idShop;
    }

    public Integer getIdShop() {
        return idShop;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setIsFizic(String isFizic) {
        this.isFizic = isFizic;
    }

    public String getIsFizic() {
        return isFizic;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSite() {
        return site;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
