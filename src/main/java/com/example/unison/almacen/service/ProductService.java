package com.example.unison.almacen.service;

import com.example.unison.almacen.model.Products;
import com.example.unison.almacen.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Products> getAllProducts() {return productRepository.findAll();}

    public Products getProductById(Long id) {return productRepository.findById(id).orElse(null);}

    public void saveProduct(Products product) {productRepository.save(product);}

    public void deleteProductById(Long id) {productRepository.deleteById(id);}
}
