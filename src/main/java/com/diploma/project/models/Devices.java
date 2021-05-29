package com.diploma.project.models;

import org.hibernate.criterion.Order;

import javax.persistence.*;

@Entity
public class Devices {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int quantity;

    @ManyToOne
    private ClientOrder order;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    private Device device;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
    public Devices(){};
    public Devices(Device device, int quantity){
        this.device = device;
        this.quantity = quantity;
    };

}
