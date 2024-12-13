package com.yamaha.prueba.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.yamaha.prueba.dto.SalesAnalysisDTO;
import com.yamaha.prueba.dto.SalesRequestDTO;
import com.yamaha.prueba.dto.SalesResponseDTO;
import com.yamaha.prueba.dto.VehicleResponseDTO;
import com.yamaha.prueba.entities.Client;
import com.yamaha.prueba.entities.Sales;
import com.yamaha.prueba.entities.Vehicle;
import com.yamaha.prueba.exceptions.BadCreateRequest;
import com.yamaha.prueba.maps.IMapSales;
import com.yamaha.prueba.maps.IMapVehicle;
import com.yamaha.prueba.repositories.ClientRepository;
import com.yamaha.prueba.repositories.SalesRepository;
import com.yamaha.prueba.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class SalesService {

    private final SalesRepository salesRepository;

    private final VehicleRepository vehicleRepository;

    private final ClientRepository clientRepository;

    private final IMapSales mapSales;

    private final IMapVehicle mapVehicle;

    public SalesService(SalesRepository salesRepository, VehicleRepository vehicleRepository, ClientRepository clientRepository, IMapSales mapSales, IMapVehicle mapVehicle) {
        this.salesRepository = salesRepository;
        this.vehicleRepository = vehicleRepository;
        this.clientRepository = clientRepository;
        this.mapSales = mapSales;
        this.mapVehicle = mapVehicle;
    }

    public SalesResponseDTO saveSale(SalesRequestDTO salesRequestDTO) {
        Vehicle vehicle = vehicleRepository.findById(salesRequestDTO.getIdVehicle()).orElseThrow(()-> new BadCreateRequest("El vehiculo no existe"));
        Client client = clientRepository.findById(salesRequestDTO.getIdClient()).orElseThrow(()-> new BadCreateRequest("El cliente no existe"));

        if (salesRequestDTO.getPrice() < 0) {
            throw new BadCreateRequest("El precio no es valido");
        }
        if (salesRequestDTO.getSalesPerson() == null || salesRequestDTO.getSalesPerson().isEmpty()) {
            throw new BadCreateRequest("El vendedor no puede estar vacio");
        }
        if (salesRequestDTO.getCity() == null || salesRequestDTO.getCity().isEmpty()) {
            throw new BadCreateRequest("La ciudad no puede estar vacia");
        }
        if (salesRequestDTO.getDate() == null) {
            throw new BadCreateRequest("La fecha no puede estar vacia");
        }
        if (salesRequestDTO.getInvoiceNumber() == null || salesRequestDTO.getInvoiceNumber().isEmpty()) {
            throw new BadCreateRequest("El numero de factura no puede estar vacio");
        }
        if (salesRequestDTO.getStore() == null || salesRequestDTO.getStore().isEmpty()) {
            throw new BadCreateRequest("La tienda no puede estar vacia");
        }

        Sales sales = Sales.builder()
                .city(salesRequestDTO.getCity())
                .date(salesRequestDTO.getDate())
                .client(client)
                .salesPerson(salesRequestDTO.getSalesPerson())
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

    public List<SalesResponseDTO> importSalesFromCsv(String filePath) throws IOException, CsvValidationException {
        List<Sales> salesList = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] values;
            csvReader.readNext();
            while ((values = csvReader.readNext()) != null) {
                Sales sale = new Sales();
                sale.setDate(LocalDate.parse(values[0]));  // Asumiendo que la fecha es válida
                sale.setInvoiceNumber(values[1]);
                sale.setCity(values[2]);
                sale.setStore(values[3]);
                sale.setPrice(Double.parseDouble(values[4]));

                // Obtener el vehículo relacionado
                Vehicle vehicle = vehicleRepository.findByEngineNumber(values[5])
                        .orElseThrow(() -> new BadCreateRequest("Vehículo con número de motor no encontrado"));

                // Obtener el cliente relacionado
                Client client = clientRepository.findById(Long.parseLong(values[6]))  // Asumiendo que el ID del cliente está en la columna 6
                        .orElseThrow(() -> new BadCreateRequest("Cliente con ID  no encontrado"));

                // Establecer el vehículo y el cliente en la venta
                sale.setVehicle(vehicle);
                sale.setClient(client);
                sale.setSalesPerson(values[7]);  // Vendedor

                salesList.add(sale);
            }
        }
        return mapSales.mapSalesList(salesRepository.saveAll(salesList));
    }

    public List<SalesAnalysisDTO> salesAnalysis(){
        List<Client> clientList = salesRepository.findClientsWithMoreThanTwoSales();
        List<SalesAnalysisDTO> salesAnalysisDTOList = new ArrayList<>();

        for (Client client : clientList) {
            SalesAnalysisDTO salesAnalysisDTO = new SalesAnalysisDTO();
            List<VehicleResponseDTO> vehicleResponseDTOList = mapVehicle
                    .mapVehicleList(client.getSales().stream()
                    .map(Sales::getVehicle) // Obtener el vehículo de cada venta
                    .distinct() // Evitar duplicados
                    .toList());
            salesAnalysisDTO.setVehicles(vehicleResponseDTOList);
            salesAnalysisDTO.setName(client.getNames());
            salesAnalysisDTO.setIdCliente(client.getClientId());
            salesAnalysisDTO.setLastName(client.getLastName());

            List<Sales> salesList = client.getSales();
            double periodicity = calculateSalesPeriodicity(salesList);
            salesAnalysisDTO.setPeriodicity(periodicity);

            LocalDate lastSaleDate = salesList.stream()
                    .max(Comparator.comparing(Sales::getDate))
                    .get()
                    .getDate();
            LocalDate nextSaleEstimate = lastSaleDate.plusDays((long) periodicity);
            salesAnalysisDTO.setNextSaleEstimate(nextSaleEstimate);

            salesAnalysisDTOList.add(salesAnalysisDTO);
        }
        return salesAnalysisDTOList;
    }

    private double calculateSalesPeriodicity(List<Sales> salesList) {
        salesList.sort(Comparator.comparing(Sales::getDate));

        long totalDays = 0;
        for (int i = 1; i < salesList.size(); i++) {
            LocalDate previousDate = salesList.get(i - 1).getDate();
            LocalDate currentDate = salesList.get(i).getDate();
            totalDays += java.time.temporal.ChronoUnit.DAYS.between(previousDate, currentDate);
        }

        return (double) totalDays / (salesList.size() - 1);
    }

}
