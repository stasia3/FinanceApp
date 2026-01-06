package com.example.demo.model;


public class Item {
    private Integer idItem;
    private String name;
    private String description;
    private String type;
    private String image;

    public Item() {}

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return idItem + " " + name  ;
    }
}

