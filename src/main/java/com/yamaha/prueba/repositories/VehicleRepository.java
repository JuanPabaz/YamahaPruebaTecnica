package com.yamaha.prueba.repositories;

import com.yamaha.prueba.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("SELECT v FROM Vehicle AS v WHERE v.engineNumber = :engineNumber ")
    Optional<Vehicle> findByEngineNumber(String engineNumber);
}
