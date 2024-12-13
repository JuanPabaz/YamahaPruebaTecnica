package com.yamaha.prueba.repositories;

import com.yamaha.prueba.entities.User;
import com.yamaha.prueba.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("SELECT u.role FROM User u WHERE u.username = :nombre")
    Role findRoleByUsername(String nombre);

}
