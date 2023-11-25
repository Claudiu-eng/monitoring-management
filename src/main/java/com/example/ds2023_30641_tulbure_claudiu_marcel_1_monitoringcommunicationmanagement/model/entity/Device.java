package com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "device")
public class Device {

    @Id
    private UUID id;

    private Integer maximHourlyEnergy;

    private Float currentConsumption;

    @OneToMany(mappedBy = "device",fetch = FetchType.EAGER)
    private List<Measure> measureList;

    @PreRemove
    private void preRemove() {
        for (Measure measure : measureList) {
            measure.setDevice(null);
        }
    }

}
