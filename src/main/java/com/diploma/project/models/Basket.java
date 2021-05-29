package com.diploma.project.models;

import javax.persistence.*;
import java.util.*;

@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Device> items;

    public boolean isEmpty(){
        if(items!=null){
            return items.isEmpty();
        }
        return true;
    }

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

    public Set<Device> getItems() {
        return items;
    }

    public void setItems(Set<Device> items) {
        this.items = items;
    }
}
