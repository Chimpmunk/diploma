package com.diploma.project.repo;

import com.diploma.project.models.Device;
import org.springframework.data.repository.CrudRepository;


public interface DeviceRepo extends CrudRepository<Device, Long> {
    public Device findOneById(long id);
}
