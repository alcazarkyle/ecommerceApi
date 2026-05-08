package com.ws101.alcazar.ecommerceApi.service;

import com.ws101.alcazar.ecommerceApi.model.Product;
import com.ws101.alcazar.ecommerceApi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class handling business logic for Products.
 * Now uses ProductRepository instead of ArrayList.
 */
@Service
public class ProductService {

    // Inject the Repository
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get single product
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Create or Update product
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Delete product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // Find by category
    public List<Product> getProductsByCategory(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }

    // Find by price range
    public List<Product> getProductsByPriceRange(double min, double max) {
        return productRepository.findByPriceBetween(min, max);
    }
}