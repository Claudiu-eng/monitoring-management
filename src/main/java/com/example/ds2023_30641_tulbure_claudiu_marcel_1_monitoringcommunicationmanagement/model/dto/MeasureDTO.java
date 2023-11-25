package com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.model.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MeasureDTO {

    @NonNull
    private Long timestamp;
    @NonNull
    private UUID deviceId;
    @NonNull
    private Float measuredValue;

}
