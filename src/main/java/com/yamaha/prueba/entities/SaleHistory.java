package com.yamaha.prueba.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "sales_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historialId;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Client cliente;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehicle vehiculo;

    @Column(nullable = false)
    private LocalDate fechaCompra;

    // Getters and Setters
}