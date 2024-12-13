package com.yamaha.prueba.repositories;

import com.yamaha.prueba.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
