package com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.repository;

import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.model.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {

}
