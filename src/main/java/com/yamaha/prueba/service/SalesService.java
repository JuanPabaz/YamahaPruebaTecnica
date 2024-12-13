package com.yamaha.prueba.service;

import com.yamaha.prueba.dto.SalesRequestDTO;
import com.yamaha.prueba.dto.SalesResponseDTO;
import com.yamaha.prueba.entities.Client;
import com.yamaha.prueba.entities.Sales;
import com.yamaha.prueba.entities.Vehicle;
import com.yamaha.prueba.exceptions.BadCreateRequest;
import com.yamaha.prueba.maps.IMapSales;
import com.yamaha.prueba.repositories.ClientRepository;
import com.yamaha.prueba.repositories.SalesRepository;
import com.yamaha.prueba.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {

    private final SalesRepository salesRepository;

    private final VehicleRepository vehicleRepository;

    private final ClientRepository clientRepository;

    private final IMapSales mapSales;

    public SalesService(SalesRepository salesRepository, VehicleRepository vehicleRepository, ClientRepository clientRepository, IMapSales mapSales) {
        this.salesRepository = salesRepository;
        this.vehicleRepository = vehicleRepository;
        this.clientRepository = clientRepository;
        this.mapSales = mapSales;
    }

    public SalesResponseDTO saveSale(SalesRequestDTO salesRequestDTO) {
        Vehicle vehicle = vehicleRepository.findById(salesRequestDTO.getIdVehicle()).orElseThrow(()-> new BadCreateRequest("El vehiculo no existe"));
        Client client = clientRepository.findById(salesRequestDTO.getIdClient()).orElseThrow(()-> new BadCreateRequest("El cliente no existe"));

        if (salesRequestDTO.getPrice() < 0) {
            throw new BadCreateRequest("El precio no es valido");
        }
        if (salesRequestDTO.getSalesPersona().isEmpty()) {
            throw new BadCreateRequest("El vendedor no puede estar vacio");
        }
        if (salesRequestDTO.getCity().isEmpty()) {
            throw new BadCreateRequest("La ciudad no puede estar vacia");
        }
        if (salesRequestDTO.getDate() == null) {
            throw new BadCreateRequest("La fecha no puede estar vacia");
        }
        if (salesRequestDTO.getInvoiceNumber() == null) {
            throw new BadCreateRequest("El numero de factura no puede estar vacio");
        }
        if (salesRequestDTO.getStore() == null) {
            throw new BadCreateRequest("La tienda no puede estar vacia");
        }

        Sales sales = Sales.builder()
                .city(salesRequestDTO.getCity())
                .date(salesRequestDTO.getDate())
                .client(client)
                .salesPerson(salesRequestDTO.getSalesPersona())
                .store(salesRequestDTO.getStore())
                .vehicle(vehicle)
                .price(salesRequestDTO.getPrice())
                .InvoiceNumber(salesRequestDTO.getInvoiceNumber())
                .build();

        return mapSales.mapSales(salesRepository.save(sales));
    }

    public List<SalesResponseDTO> getAllSales() {
        return mapSales.mapSalesList(salesRepository.findAll());
    }
}
