package com.yamaha.prueba.maps;

import com.yamaha.prueba.dto.SalesResponseDTO;
import com.yamaha.prueba.dto.VehicleResponseDTO;
import com.yamaha.prueba.entities.Sales;
import com.yamaha.prueba.entities.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapVehicle {

    @Mappings({
            @Mapping(source = "vehicleId", target = "vehicleId"),
            @Mapping(source = "engineNumber",target = "engineNumber"),
            @Mapping(source = "model", target = "model"),
            @Mapping(source = "cylinderCapacity", target = "cylinderCapacity"),
            @Mapping(source = "color", target = "color"),
            @Mapping(source = "dateOfAssembly", target = "dateOfAssembly"),
            @Mapping(source = "yearModel", target = "yearModel")
    })
    VehicleResponseDTO mapVehicle(Vehicle vehicle);

    List<VehicleResponseDTO> mapVehicleList(List<Vehicle> vehicles);
}
