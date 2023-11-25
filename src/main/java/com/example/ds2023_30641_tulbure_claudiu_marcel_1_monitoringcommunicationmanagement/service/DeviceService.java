package com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.service;

import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.exception.model.DeviceNotFoundException;
import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.model.dto.MeasureDTO;
import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.model.dto.MeasureDTOForGraph;
import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.model.entity.Device;
import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.model.entity.Measure;
import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.model.mapper.MeasureMapper;
import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.repository.DeviceRepository;
import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.repository.MeasureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final MeasureMapper measureMapper;

    public List<MeasureDTOForGraph> getMeasureDTOList(UUID deviceId, LocalDate date) throws DeviceNotFoundException {
        Device device = deviceRepository.findById(deviceId).orElseThrow(DeviceNotFoundException::new);

        LocalDateTime endOfDayS = LocalDateTime.of(date, LocalTime.MAX);
        Instant endOfDayInstant = endOfDayS.toInstant(ZoneOffset.UTC);
        Long endOfDayTimestamp = endOfDayInstant.getEpochSecond();
        LocalDateTime startOfDay = LocalDateTime.of(date, LocalTime.MIN);
        Instant startOfDayInstant = startOfDay.toInstant(ZoneOffset.UTC);
        Long startOfDayTimestamp = startOfDayInstant.getEpochSecond();

        return device.getMeasureList().stream()
                .filter(measure -> measure.getTimestamp() >= startOfDayTimestamp && measure.getTimestamp() <= endOfDayTimestamp)
                .map(measureMapper::entityToDTOForGraph)
                .collect(Collectors.toList());


    }

}
