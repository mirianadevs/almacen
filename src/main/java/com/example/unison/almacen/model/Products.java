package com.example.unison.almacen.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Long price;

    @Column(name = "date", updatable = false)
    private LocalDate date;

    @PrePersist
    protected void onCreate() {
        date = LocalDate.now();
    }
}
