package com.yamaha.prueba.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;

    @Column(nullable = false, unique = true, length = 50)
    private String engineNumber;

    @Column(nullable = false, length = 50)
    private String model;

    @Column(nullable = false)
    private Integer cylinderCapacity;

    @Column(nullable = false, length = 30)
    private String color;

    @Column(nullable = false)
    private LocalDate dateOfAssembly;

    @Column(nullable = false)
    private Integer yearModel;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    private List<Sales> ventas;

    // Getters and Setters
}
