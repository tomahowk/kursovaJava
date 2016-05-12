package com.andrusiak.domain;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String goodType;
    private String manufacturer;
    private String itemName;
    private int price;
    private String spec;
    private int storage;

    public Item(int id,
                String name,
                String manufacturer,
                String itemName,
                int price,
                String spec,
                int storage
                ) {
        this.id = id;
        this.goodType = name;
        this.manufacturer = manufacturer;
        this.itemName = itemName;
        this.price = price;
        this.spec = spec;
        this.storage = storage;
    }

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }


    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + goodType + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", spec=" + spec +
                ", storage='" + storage + '\'' +
                '}';
    }
}

