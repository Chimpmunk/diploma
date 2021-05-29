package com.diploma.project.repo;

import com.diploma.project.models.Basket;
import com.diploma.project.models.User;
import org.springframework.data.repository.CrudRepository;

public interface BasketRepo extends CrudRepository<Basket, Long> {
    public Basket findByUser(User usr);
}
