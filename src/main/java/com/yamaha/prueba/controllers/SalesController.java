package com.yamaha.prueba.controllers;

import com.opencsv.exceptions.CsvValidationException;
import com.yamaha.prueba.dto.SalesRequestDTO;
import com.yamaha.prueba.service.SalesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/sales")
public class SalesController {

    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @PostMapping
    public ResponseEntity<?> saveSale(@RequestBody SalesRequestDTO salesRequestDTO) {
        return ResponseEntity.ok(salesService.saveSale(salesRequestDTO));
    }

    @GetMapping
    public ResponseEntity<?> getAllSales() {
        return ResponseEntity.ok(salesService.getAllSales());
    }

    @PostMapping("/import")
    public String importSales(@RequestParam("file") MultipartFile file) {
        try {
            String filePath = "C:/Users/jpgth/OneDrive/Escritorio/prueba/src/main/java/com/yamaha/prueba/csv";
            file.transferTo(new java.io.File(filePath));

            salesService.importSalesFromCsv(filePath);

            return "Ventas importadas exitosamente";
        } catch (IOException e) {
            return "Error al importar ventas: " + e.getMessage();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
