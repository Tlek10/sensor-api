package com.example.sensor_api.repository;

import com.example.sensor_api.entities.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    @Query("SELECT COUNT(m) FROM Measurement m WHERE m.raining = true")
    Long countRainyDays();
}
