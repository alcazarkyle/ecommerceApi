package com.ws101.alcazar.ecommerceApi.controller;

import com.ws101.alcazar.ecommerceApi.model.Product;
import com.ws101.alcazar.ecommerceApi.service.ProductService;
// CORRECTED IMPORT STATEMENTS FOR CUSTOM EXCEPTIONS
import com.ws101.alcazar.ecommerceApi.exception.ProductNotFoundException;
import com.ws101.alcazar.ecommerceApi.exception.InvalidInputException;


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

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        // Use orElseThrow to throw ProductNotFoundException if the product is not found
        return productService.getProductById(id)
                           .map(ResponseEntity::ok) // If found, return with 200 OK
                           .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found."));
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
                    // Throw InvalidInputException for bad price format
                    throw new InvalidInputException("Invalid price format: '" + filterValue + "'. Please provide a valid number.");
                }
                break;
            default:
                // Throw InvalidInputException for unknown filter type
                throw new InvalidInputException("Unknown filter type: '" + filterType + "'. Valid types are name, category, price.");
        }
        // If filtering is successful, return OK (200)
        return ResponseEntity.ok(products);
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        // Assuming addProduct succeeds or throws another exception (handled by GlobalExceptionHandler)
        Product created = productService.addProduct(product);
        // Return the created product with status CREATED (201)
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // UPDATE (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        // Use orElseThrow to handle the Optional returned by productService.updateProduct
        // If updateProduct returns an empty Optional, it means the product wasn't found.
        return productService.updateProduct(id, product)
                           .map(ResponseEntity::ok) // If update successful, return updated product with 200 OK
                           .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found for update."));
    }

    // PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<Product> patchProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        // Retrieve the existing product first, throw if not found
        Optional<Product> existingOptional = productService.getProductById(id);
        if (!existingOptional.isPresent()) {
             throw new ProductNotFoundException("Product with ID " + id + " not found for patching.");
        }
        Product existing = existingOptional.get();

        // Apply updates only if the fields are not null or default values
        // Note: For price and stockQuantity, check if they are explicitly set if 0 is a valid value.
        // If 0 means "no change" or "not provided", this logic is fine. If 0 is a valid quantity/price,
        // you might need to use wrapper types (Integer, Double) or a different approach to detect if the field was sent.
        if (product.getName() != null) existing.setName(product.getName());
        if (product.getDescription() != null) existing.setDescription(product.getDescription());
        // Assuming 0 price means no change or not provided. Adjust if 0 is a valid price.
        if (product.getPrice() != 0) existing.setPrice(product.getPrice());
        if (product.getCategory() != null) existing.setCategory(product.getCategory());
        // Assuming 0 stock quantity means no change or not provided. Adjust if 0 is a valid quantity.
        if (product.getStockQuantity() != 0) existing.setStockQuantity(product.getStockQuantity());
        if (product.getImageUrl() != null) existing.setImageUrl(product.getImageUrl());

        // Use the updateProduct method which returns Optional<Product>
        // If the update is successful, map it to ResponseEntity::ok (200 OK)
        return productService.updateProduct(id, existing)
                           .map(ResponseEntity::ok)
                           // If updateProduct returns an empty Optional after we found the product,
                           // it indicates an internal server error during the update process.
                           .orElseThrow(() -> new RuntimeException("Failed to update product with ID " + id + " after finding it."));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        // First, check if the product exists. If not, throw ProductNotFoundException.
        // We need to get the product to ensure it exists before attempting deletion.
        return productService.getProductById(id)
                           .map(product -> {
                               // If product exists, attempt deletion using the service.
                               boolean deleted = productService.deleteProduct(id);
                               if (deleted) {
                                   // Return 204 No Content on successful deletion.
                                   return ResponseEntity.noContent().build();
                               } else {
                                   // This case implies the product existed but deletion failed in the service layer.
                                   // This might be an internal server error.
                                   throw new RuntimeException("Failed to delete product with ID " + id + " despite it existing.");
                               }
                           })
                           // If getProductById returned an empty Optional, the product doesn't exist.
                           .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found for deletion."));
    }
}