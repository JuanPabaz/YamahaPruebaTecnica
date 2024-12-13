package com.yamaha.prueba.maps;

import com.yamaha.prueba.dto.ClientResponseDTO;
import com.yamaha.prueba.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapClient {

    @Mappings({
            @Mapping(source = "identificationNumber", target = "identificationNumber"),
            @Mapping(source = "names",target = "names"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "address", target = "address"),
            @Mapping(source = "dateOfBirth", target = "dateOfBirth"),
            @Mapping(source = "gender", target = "gender"),
            @Mapping(source = "cellPhone", target = "cellPhone"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "user.idUser", target = "idUser")
    })
    ClientResponseDTO mapClient(Client client);

    List<ClientResponseDTO> mapClientList(List<Client> clientList);
}
