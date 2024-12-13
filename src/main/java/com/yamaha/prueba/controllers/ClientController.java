package com.yamaha.prueba.controllers;

import com.yamaha.prueba.dto.ClientRequestDTO;
import com.yamaha.prueba.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<?> saveClient(@RequestBody ClientRequestDTO clientRequestDTO) {
        return ResponseEntity.ok(clientService.saveClient(clientRequestDTO));
    }

    @GetMapping
    public ResponseEntity<?> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{idClient}")
    public ResponseEntity<?> getClientById(@PathVariable Long idClient) {
        return ResponseEntity.ok(clientService.getClientById(idClient));
    }
}
