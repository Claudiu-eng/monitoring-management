package com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.controller;

import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.exception.model.DeviceNotFoundException;
import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/monitoring")
public class MonitoringController {

    private final DeviceService deviceService;


    @GetMapping("/{deviceId}/{date}")
    public ResponseEntity getDeviceMeasure(@PathVariable UUID deviceId, @PathVariable String date) throws DeviceNotFoundException {
        LocalDate targetDate = LocalDate.parse(date);
        return ResponseEntity.ok(deviceService.getMeasureDTOList(deviceId, targetDate));
    }


}
