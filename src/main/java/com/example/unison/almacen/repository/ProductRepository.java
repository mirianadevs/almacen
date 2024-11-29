package com.example.unison.almacen.repository;

import com.example.unison.almacen.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products, Long> {
}
