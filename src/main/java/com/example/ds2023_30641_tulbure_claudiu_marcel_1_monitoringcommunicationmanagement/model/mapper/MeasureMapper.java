package com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.model.mapper;

import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.model.dto.DeviceDTO;
import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.model.dto.MeasureDTO;
import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.model.dto.MeasureDTOForGraph;
import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.model.entity.Device;
import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.model.entity.Measure;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MeasureMapper {

    public Measure deviceDTOToEntity(MeasureDTO measureDTO, Device device) {
        return Measure.builder()
                .timestamp(measureDTO.getTimestamp())
                .device(device)
                .value(measureDTO.getMeasuredValue())
                .build();
    }
    public MeasureDTOForGraph entityToDTOForGraph(Measure measure) {
        return MeasureDTOForGraph.builder()
                .date(measure.getTimestamp())
                .value(measure.getValue())
                .build();
    }

}
