package com.yamaha.prueba.repositories;

import com.yamaha.prueba.entities.Client;
import com.yamaha.prueba.entities.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalesRepository extends JpaRepository<Sales, Integer> {

    @Query("SELECT c FROM Client c JOIN c.sales s GROUP BY c.clientId HAVING COUNT(s.saleId) > 2")
    List<Client> findClientsWithMoreThanTwoSales();
}
