package com.yamaha.prueba.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "frecuent_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
