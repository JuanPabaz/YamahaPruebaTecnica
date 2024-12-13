package com.yamaha.prueba.service;

import com.yamaha.prueba.dto.VehicleRequestDTO;
import com.yamaha.prueba.dto.VehicleResponseDTO;
import com.yamaha.prueba.entities.Vehicle;
import com.yamaha.prueba.exceptions.BadCreateRequest;
import com.yamaha.prueba.maps.IMapVehicle;
import com.yamaha.prueba.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    private final IMapVehicle mapVehicle;

    public VehicleService(VehicleRepository vehicleRepository, IMapVehicle mapVehicle) {
        this.vehicleRepository = vehicleRepository;
        this.mapVehicle = mapVehicle;
    }

    public VehicleResponseDTO saveVehicle(VehicleRequestDTO vehicleRequestDTO) {
        if (vehicleRequestDTO.getColor().isEmpty()) {
            throw new BadCreateRequest("El color no puede estar vacio");
        }
        if (vehicleRequestDTO.getModel().isEmpty()){
            throw new BadCreateRequest("El model no puede estar vacio");
        }
        if (vehicleRequestDTO.getCylinderCapacity() == null){
            throw new BadCreateRequest("El cilindraje no puede estar vacio");
        }
        if (vehicleRequestDTO.getEngineNumber().isEmpty()){
            throw new BadCreateRequest("El numero del motor no puede estar vacio");
        }
        if (vehicleRequestDTO.getDateOfAssembly() == null){
            throw new BadCreateRequest("La fecha de ensamblaje no puede estar vacia");
        }
        if (vehicleRequestDTO.getYearModel() == null){
            throw new BadCreateRequest("La fecha de modelo no puede estar vacia");
        }

        Vehicle vehicle = Vehicle.builder()
                .color(vehicleRequestDTO.getColor())
                .model(vehicleRequestDTO.getModel())
                .cylinderCapacity(vehicleRequestDTO.getCylinderCapacity())
                .engineNumber(vehicleRequestDTO.getEngineNumber())
                .dateOfAssembly(vehicleRequestDTO.getDateOfAssembly())
                .yearModel(vehicleRequestDTO.getYearModel())
                .build();

        return mapVehicle.mapVehicle(vehicleRepository.save(vehicle));
    }

    public List<VehicleResponseDTO> getAllVehicles() {
        return mapVehicle.mapVehicleList(vehicleRepository.findAll());
    }
}
