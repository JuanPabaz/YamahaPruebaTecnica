package com.yamaha.prueba.repositories;

import com.yamaha.prueba.entities.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales, Integer> {
}
