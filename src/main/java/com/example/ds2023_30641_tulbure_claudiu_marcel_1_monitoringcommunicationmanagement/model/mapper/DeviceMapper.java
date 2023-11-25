package com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.model.mapper;

import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.model.dto.DeviceDTO;
import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.model.entity.Device;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DeviceMapper {

    public Device deviceDTOToEntity(DeviceDTO deviceDTO) {
        return Device.builder()
                .id(deviceDTO.getUuid())
                .maximHourlyEnergy(deviceDTO.getMaximHourlyEnergy())
                .currentConsumption(0.0f)
                .build();
    }

}
