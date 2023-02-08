package com.example.connect_five.models;

public class CentroSportivo {
    private String name;
    private String city;
    private String street;
    private String manager;
    private String image;
    private Float fieldPrice;
    private Float refereePrice;
    private Float tunics;

    public CentroSportivo(String nm, String ct, String str, String mng, String img, Float field_price){
        this.name = nm;
        this.city = ct;
        this.street = str;
        this.manager = mng;
        this.image = img;
        this.fieldPrice = field_price;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Float getFieldPrice() {
        return fieldPrice;
    }

    public void setFieldPrice(Float fieldPrice) {
        this.fieldPrice = fieldPrice;
    }

    public Float getRefereePrice() {
        return refereePrice;
    }

    public void setRefereePrice(Float refereePrice) {
        this.refereePrice = refereePrice;
    }

    public Float getTunics() {
        return tunics;
    }

    public void setTunics(Float tunics) {
        this.tunics = tunics;
    }
}
