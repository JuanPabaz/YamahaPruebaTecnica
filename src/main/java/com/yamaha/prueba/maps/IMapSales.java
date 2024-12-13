package com.yamaha.prueba.maps;

import com.yamaha.prueba.dto.SalesResponseDTO;
import com.yamaha.prueba.entities.Sales;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapSales {

    @Mappings({
            @Mapping(source = "saleId", target = "saleId"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "invoiceNumber",target = "invoiceNumber"),
            @Mapping(source = "city", target = "city"),
            @Mapping(source = "store", target = "store"),
            @Mapping(source = "price", target = "price"),
            @Mapping(source = "vehicle.idVehicle", target = "idVehicle"),
            @Mapping(source = "client.idClient", target = "idClient"),
            @Mapping(source = "salesPersona", target = "salesPersona")
    })
    SalesResponseDTO mapSales(Sales sales);

    List<SalesResponseDTO> mapSalesList(List<Sales> salesList);
}
