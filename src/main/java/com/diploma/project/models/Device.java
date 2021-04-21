package com.diploma.project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String image;

    private String name;

    private String devModel;

    private String characteristics;

    private String price;
    public Device(){};

    public Device(String name, String devModel, String characteristics, String price){
        this.name=name;
        this.devModel = devModel;
        this.characteristics=characteristics;
        this.price=price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String rice) {
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
