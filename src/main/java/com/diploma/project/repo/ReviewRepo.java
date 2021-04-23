package com.diploma.project.repo;

import com.diploma.project.models.Device;
import com.diploma.project.models.Review;
import com.diploma.project.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepo extends CrudRepository<Review, Long> {
    public Review findByUser(User u);
    public List<Review> findByDevice(Device d);
}
