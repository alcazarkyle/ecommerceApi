package com.ws101.alcazar.ecommerceApi.service;

import com.ws101.alcazar.ecommerceApi.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private List<Product> products = new ArrayList<>();
    private long nextId = 1;

    // Constructor with sample data
    public ProductService() {
        products.add(new Product(nextId++, "Phone", "Smartphone", 10000, "Electronics", 10, ""));
        products.add(new Product(nextId++, "Laptop", "Gaming Laptop", 50000, "Electronics", 5, ""));
    }

    // Get all products
    public List<Product> getAllProducts() {
        return products;
    }

    // Get product by ID
    public Optional<Product> getProductById(long id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst();
    }

    // Add product
    public Product addProduct(Product product) {
        product.setId(nextId++);
        products.add(product);
        return product;
    }

    // Update product
    public Optional<Product> updateProduct(long id, Product updatedProduct) {
        Optional<Product> existingProduct = getProductById(id);

        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            product.setCategory(updatedProduct.getCategory());
           product.setStockQuantity(updatedProduct.getStockQuantity());
            product.setImageUrl(updatedProduct.getImageUrl());
            return Optional.of(product);
        }

        return Optional.empty();
    }

    // Delete product
    public boolean deleteProduct(long id) {
        return products.removeIf(product -> product.getId() == id);
    }
}