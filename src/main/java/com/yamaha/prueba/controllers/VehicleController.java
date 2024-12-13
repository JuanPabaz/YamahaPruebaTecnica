package com.yamaha.prueba.controllers;

import com.yamaha.prueba.dto.VehicleRequestDTO;
import com.yamaha.prueba.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<?> registrarVehiculo(@RequestBody VehicleRequestDTO vehicleRequestDTO) {
        return ResponseEntity.ok(vehicleService.saveVehicle(vehicleRequestDTO));
    }

    @GetMapping
    public ResponseEntity<?> obtenerVehiculos() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }
}
