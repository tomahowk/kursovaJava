package com.andrusiak.domain;

import java.io.Serializable;


public class Customer implements Serializable {
    private int id;
    private int bought;
    private String name;
    private int digits;
    private String delivery;
    private String adress;

    public Customer(int id,
                    int bought,
                    String name,
                    int digits,
                    String delivery,
                    String adress) {
        this.id = id;
        this.bought = bought;
        this.name = name;
        this.digits = digits;
        this.delivery = delivery;
        this.adress = adress;
    }

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBought() {
        return bought;
    }

    public void setBought(int bought) {
        this.bought = bought;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDigits() {
        return digits;
    }

    public void setDigits(int digits) {
        this.digits = digits;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", bought='" + bought + '\'' +
                ", name='" + name + '\'' +
                ", digits=" + digits +
                ", delivery=" + delivery +
                ", adress=" + adress +
                '}';
    }


}
