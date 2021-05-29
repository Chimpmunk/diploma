package com.diploma.project.models;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
public class ClientOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public ClientOrder(){}

    public ClientOrder(User user, Set<Devices> devices){
        this.user=user;
        this.items=devices;
        this.isCompleted=false;
        for(Devices d: devices){
            this.total+=(d.getQuantity()*d.getDevice().getPrice());
        }
    }

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private boolean isCompleted;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Devices> items;

    private double total;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Set<Devices> getItems() {
        return items;
    }

    public void setItems(Set<Devices> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
