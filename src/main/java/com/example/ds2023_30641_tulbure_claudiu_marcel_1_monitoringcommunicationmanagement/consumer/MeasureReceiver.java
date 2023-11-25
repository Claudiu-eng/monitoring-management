package com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.consumer;

import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.exception.model.DeviceNotFoundException;
import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.model.dto.MeasureDTO;
import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.model.entity.Device;
import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.model.mapper.MeasureMapper;
import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.repository.DeviceRepository;
import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.repository.MeasureRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MeasureReceiver {

    private final DeviceRepository deviceRepository;
    private final MeasureRepository measureRepository;
    private final MeasureMapper measureMapper;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final Map<UUID, Integer> values;
    private final Map<UUID, Float> measuredValues;


    @RabbitListener(queues = {"${queue-value.name}"})
    public void receiveMessage(String message) {


        try {
            ObjectMapper objectMapper = new ObjectMapper();
            MeasureDTO myObject = objectMapper.readValue(message, MeasureDTO.class);
            Device device = deviceRepository.findById(myObject.getDeviceId()).orElseThrow(DeviceNotFoundException::new);
            log.info("Received message from queue , deviceId =  "+ myObject.getDeviceId()+" , measuredValue = "+myObject.getMeasuredValue());
            if (values.containsKey(device.getId())) {
                int val = values.get(device.getId());
                if (val == 0) {
                    values.put(device.getId(), 1);
                    measuredValues.put(device.getId(), myObject.getMeasuredValue());
                } else if (val == 5) {
                    myObject.setMeasuredValue(myObject.getMeasuredValue() - measuredValues.get(device.getId()));
                    measureRepository.save(measureMapper.deviceDTOToEntity(myObject, device));
                    values.put(device.getId(), 0);
                    if (myObject.getMeasuredValue() > device.getMaximHourlyEnergy()) {
                        this.simpMessagingTemplate.convertAndSend(
                                "/topic/socket/notifications/" + device.getId(), "device with id = " + device.getId() +
                                        " has exceeded the maximum hourly energy");
                        log.warn("Device with id = " + device.getId() +
                                " has exceeded the maximum hourly energy");
                    }
                } else {
                    values.put(device.getId(), val + 1);
                }
            } else {
                values.put(device.getId(), 1);
                measuredValues.put(device.getId(), myObject.getMeasuredValue());
            }

        } catch (JsonProcessingException | DeviceNotFoundException exception) {
            exception.printStackTrace();
        }


    }


}
