package com.yamaha.prueba.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "sales")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ventaId;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false, unique = true, length = 20)
    private String numeroFactura;

    @Column(nullable = false, length = 50)
    private String ciudad;

    @Column(nullable = false, length = 100)
    private String tienda;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal precio;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehicle vehiculo;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Client cliente;

    @Column(nullable = false, length = 100)
    private String vendedor;

    // Getters and Setters
}
