package com.diploma.project.repo;

import com.diploma.project.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    public User findByUsername(String username);
    public User findById(long id);

}
