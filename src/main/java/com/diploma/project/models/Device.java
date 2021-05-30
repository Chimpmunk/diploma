package com.diploma.project.models;

import javax.persistence.*;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String image;

    private String name;

    private String devModel;

    @Column(columnDefinition="LONGTEXT")
    private String characteristics;

    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    private double price;
    public Device(){};

    public Device(String name, String devModel, String characteristics, double price, Category category){
        this.name=name;
        this.devModel = devModel;
        this.characteristics=characteristics;
        this.price=price;
        this.category = category;
    }



    public double getPrice() {
        return price;
    }

    public void setPrice(double rice) {
        this.price = rice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDevModel() {
        return devModel;
    }

    public void setDevModel(String model) {
        this.devModel = model;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }


}
