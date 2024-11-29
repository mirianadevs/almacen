package com.example.unison.almacen.controller;

import com.example.unison.almacen.model.Products;
import com.example.unison.almacen.service.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //Mostrar todos los productos
    @GetMapping
    public String getProducts(Model model) {
        //Obtener todas las notas desde el service
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    //Mostrar form para agregar nuevo producto
    @GetMapping("/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Products());
        return "add-product";
    }

    //Guardar un nuevo producto o actualizar existente
    @PostMapping("/save")
    public String saveProduct(Products product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(Model model, @PathVariable Long id) {
        Products product = productService.getProductById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "product-detail";
        }
        return "redirect:/products";
    }

    //Eliminar por ID
    @GetMapping("/delete/{id}")
    public String deleteProduct(Model model, @PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }

    //Mostrar detalles de un producto
    @GetMapping("/detail/{id}")
    public String detailProduct(Model model, @PathVariable Long id) {
        Products product = productService.getProductById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "product-detail";
        }
        return "redirect:/products";
    }

    @GetMapping("/export")
    public ResponseEntity<String> exportToCSV() {
        StringBuilder csvContent = new StringBuilder();
        csvContent.append("ID,Nombre,Descripcion,Precio,Fecha de Creacion\n");

        List<Products> products = productService.getAllProducts();
        for (Products product : products) {
            csvContent.append(product.getId()).append(",")
                    .append(product.getName()).append(",")
                    .append(product.getDescription()).append(",")
                    .append(product.getPrice()).append(",")
                    .append(product.getDate()).append("\n");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=products.csv");

        return new ResponseEntity<>(csvContent.toString(), headers, HttpStatus.OK);
    }

}
