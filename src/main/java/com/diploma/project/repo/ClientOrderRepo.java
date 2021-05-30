package com.diploma.project.repo;

import com.diploma.project.models.ClientOrder;
import com.diploma.project.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientOrderRepo extends CrudRepository<ClientOrder, Long> {
    public List<ClientOrder> findByUser(User user);
    public List<ClientOrder> findAll();
    public ClientOrder findOdeById(long id);
}
