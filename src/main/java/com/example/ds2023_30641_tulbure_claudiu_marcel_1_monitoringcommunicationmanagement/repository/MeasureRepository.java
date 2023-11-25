package com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.repository;

import com.example.ds2023_30641_tulbure_claudiu_marcel_1_monitoringcommunicationmanagement.model.entity.Measure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface MeasureRepository extends JpaRepository<Measure, UUID> {
    List<Measure> findAllByTimestampBetween(Long start, Long end);

}
