package com.yamaha.prueba.maps;

import com.yamaha.prueba.dto.UserResponseDTO;
import com.yamaha.prueba.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapUser {

    @Mappings({
            @Mapping(source = "idUser", target = "idUser"),
            @Mapping(source = "fullName",target = "fullName"),
            @Mapping(source = "role", target = "role"),
            @Mapping(source = "username", target = "username")
    })
    UserResponseDTO mapUser(User user);

    List<UserResponseDTO> mapUserList(List<User> userList);
}
