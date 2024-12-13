package com.yamaha.prueba.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "usuarios_frecuentes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FrecuentUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioFrecuenteId;

    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Client cliente;

    @Column(nullable = false, precision = 4, scale = 2)
    private Double promedioCompraMes;

    @Column(nullable = false)
    private LocalDate ultimaCompra;

    @Column(nullable = false)
    private LocalDate proximaCompra;

    // Getters and Setters
}
