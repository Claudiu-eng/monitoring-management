package com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.consumer;

import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.model.dto.DeviceDTO;
import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.model.mapper.DeviceMapper;
import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.repository.DeviceRepository;
import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.service.JwtService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceReceiver {

    private final DeviceRepository deviceRepository;
    private final JwtService jwtService;
    private final DeviceMapper deviceMapper;

    @RabbitListener(queues = {"${queue-data.name}"})
    public void receiveMessage(String message) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        DeviceDTO myObject = objectMapper.readValue(message, DeviceDTO.class);
        log.info("Received message from queue , deviceId =  "+ myObject.getUuid()+" operation = "+ myObject.getOperation());
        if (myObject.getOperation().equals("INSERT")) {
            saveDevice(myObject);
        } else if (myObject.getOperation().equals("DELETE")) {
            deleteDevice(myObject);
        }
    }


    private void saveDevice(DeviceDTO deviceDTO) {
        if (!validToken(deviceDTO.getToken())) {
            return;
        }
        if (deviceRepository.existsById(deviceDTO.getUuid())) {
            return;
        }
        deviceRepository.save(deviceMapper.deviceDTOToEntity(deviceDTO));
    }

    private void deleteDevice(DeviceDTO deviceDTO) {
        if (!validToken(deviceDTO.getToken())) {
            return;
        }
        if (!deviceRepository.existsById(deviceDTO.getUuid())) {
            return;
        }
        deviceRepository.delete(deviceMapper.deviceDTOToEntity(deviceDTO));
    }

    private boolean validToken(String token) {
        return jwtService.extractRole(token).contains("ADMIN");
    }

}
