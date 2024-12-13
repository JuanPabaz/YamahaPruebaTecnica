package com.yamaha.prueba.controllers;

import com.yamaha.prueba.dto.SalesRequestDTO;
import com.yamaha.prueba.service.SalesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
}
