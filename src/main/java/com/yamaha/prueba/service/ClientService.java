package com.yamaha.prueba.service;

import com.yamaha.prueba.dto.ClientRequestDTO;
import com.yamaha.prueba.dto.ClientResponseDTO;
import com.yamaha.prueba.entities.Client;
import com.yamaha.prueba.entities.User;
import com.yamaha.prueba.exceptions.BadCreateRequest;
import com.yamaha.prueba.maps.IMapClient;
import com.yamaha.prueba.repositories.ClientRepository;
import com.yamaha.prueba.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final IMapClient mapClient;

    public ClientService(ClientRepository clientRepository, IMapClient mapClient) {
        this.clientRepository = clientRepository;
        this.mapClient = mapClient;
    }

    public ClientResponseDTO saveClient(ClientRequestDTO clientRequestDTO) {

        if (clientRequestDTO.getCellPhone() == null || clientRequestDTO.getCellPhone().isEmpty()) {
            throw new BadCreateRequest("El numero de celular no puede estar vacio");
        }
        if (clientRequestDTO.getEmail() == null || clientRequestDTO.getEmail().isEmpty()) {
            throw new BadCreateRequest("El email no puede estar vacio");
        }
        if (clientRequestDTO.getAddress() == null || clientRequestDTO.getAddress().isEmpty()) {
            throw new BadCreateRequest("La direccion no puede estar vacia");
        }
        if (clientRequestDTO.getDateOfBirth() == null) {
            throw new BadCreateRequest("La fecha de nacimiento no puede estar vacio");
        } else {
            LocalDate today = LocalDate.now();
            Period age = Period.between(clientRequestDTO.getDateOfBirth(), today);
            if (age.getYears() < 18) {
                throw new BadCreateRequest("El usuario debe ser mayor de 18 años");
            }
        }
        if (clientRequestDTO.getGender() == null || clientRequestDTO.getGender().isEmpty()) {
            throw new BadCreateRequest("El genero no puede estar vacio");
        }
        if (clientRequestDTO.getIdentificationNumber() == null || clientRequestDTO.getIdentificationNumber().isEmpty()) {
            throw new BadCreateRequest("El numero de identification no puede estar vacio");
        }
        if (clientRequestDTO.getNames() == null || clientRequestDTO.getNames().isEmpty()) {
            throw new BadCreateRequest("El nombre no puede estar vacio");
        }
        if (clientRequestDTO.getLastName() == null || clientRequestDTO.getLastName().isEmpty()) {
            throw new BadCreateRequest("Los apellidos no pueden estar vacios");
        }
        Client client = Client.builder()
                .identificationNumber(clientRequestDTO.getIdentificationNumber())
                .address(clientRequestDTO.getAddress())
                .dateOfBirth(clientRequestDTO.getDateOfBirth())
                .gender(clientRequestDTO.getGender())
                .email(clientRequestDTO.getEmail())
                .cellPhone(clientRequestDTO.getCellPhone())
                .names(clientRequestDTO.getNames())
                .lastName(clientRequestDTO.getLastName())
                .build();

        return mapClient.mapClient(clientRepository.save(client));
    }

    public List<ClientResponseDTO> getAllClients() {
        return mapClient.mapClientList(clientRepository.findAll());
    }

    public ClientResponseDTO getClientById(Long idClient){
        Client client = clientRepository.findById(idClient).orElseThrow(() -> new BadCreateRequest("El cliente no existe"));
        return mapClient.mapClient(client);
    }
}
