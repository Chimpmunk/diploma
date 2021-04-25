package com.diploma.project.repo;

import com.diploma.project.models.Device;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface DeviceRepo extends CrudRepository<Device, Long> {
    public Device findOneById(long id);
    public List<Device> findByNameContaining (String name);
    public List<Device> findByDevModelContaining (String devModel);
    public List<Device> findByCharacteristics (String characterstics);
}
