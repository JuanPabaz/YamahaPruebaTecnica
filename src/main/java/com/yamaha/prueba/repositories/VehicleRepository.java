package com.yamaha.prueba.repositories;

import com.yamaha.prueba.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
