package com.ws101.alcazar.ecommerceApi.controller;

import com.ws101.alcazar.ecommerceApi.model.Product;
import com.ws101.alcazar.ecommerceApi.service.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional; // Import Optional
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products") // base path
public class ProductController {

    private final ProductService productService;

    // Constructor
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // GET by ID - INAYOS KO DITO
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        // Correctly handles Optional<Product> from ProductService
        return productService.getProductById(id)
                           .map(ResponseEntity::ok)
                           .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // FILTER
    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterProducts(
            @RequestParam String filterType,
            @RequestParam String filterValue) {

        List<Product> products = productService.getAllProducts();

        switch(filterType.toLowerCase()) {
            case "name":
                products = products.stream()
                        .filter(p -> p.getName().toLowerCase().contains(filterValue.toLowerCase()))
                        .collect(Collectors.toList());
                break;
            case "category":
                products = products.stream()
                        .filter(p -> p.getCategory().equalsIgnoreCase(filterValue))
                        .collect(Collectors.toList());
                break;
            case "price":
                try {
                    double maxPrice = Double.parseDouble(filterValue);
                    products = products.stream()
                            .filter(p -> p.getPrice() <= maxPrice)
                            .collect(Collectors.toList());
                } catch (NumberFormatException e) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Handle invalid price format
                }
                break;
            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Handle unknown filter type
        }

        return ResponseEntity.ok(products);
    }

    // CREATE - Changed to use addProduct from ProductService
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        // Using addProduct from ProductService to create a new product
        Product created = productService.addProduct(product);
        // Return the created product with status CREATED
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // UPDATE (PUT) - INAYOS KO DITO
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        // Correctly handles Optional<Product> from ProductService.updateProduct
        return productService.updateProduct(id, product)
                           .map(ResponseEntity::ok)
                           .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<Product> patchProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        // Correctly handles Optional<Product> from ProductService.getProductById
        Optional<Product> existingOptional = productService.getProductById(id);
        if (!existingOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Product existing = existingOptional.get();

        // Apply updates if the fields are not null or default values
        if (product.getName() != null) existing.setName(product.getName());
        if (product.getDescription() != null) existing.setDescription(product.getDescription());
        // Assuming 0 is a valid stock quantity, so check for null or a specific sentinel value if 0 is not allowed
        if (product.getPrice() != 0) existing.setPrice(product.getPrice());
        if (product.getCategory() != null) existing.setCategory(product.getCategory());
        // Assuming 0 is a valid stock quantity, so check for null or a specific sentinel value if 0 is not allowed
        if (product.getStockQuantity() != 0) existing.setStockQuantity(product.getStockQuantity());
        if (product.getImageUrl() != null) existing.setImageUrl(product.getImageUrl());

        // Use the updateProduct method which returns Optional<Product>
        return productService.updateProduct(id, existing)
                           .map(ResponseEntity::ok)
                           .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)); // Or NOT_FOUND if update fails unexpectedly
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        // Assuming ProductService.deleteProduct returns boolean
        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}