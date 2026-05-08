package com.ws101.alcazar.ecommerceapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;        // NEW IMPORT
import jakarta.validation.constraints.NotBlank;    // NEW IMPORT
import jakarta.validation.constraints.NotNull;     // NEW IMPORT
import jakarta.validation.constraints.Size;      // NEW IMPORT


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required") // NEW VALIDATION
    @Size(max = 255, message = "Product name cannot exceed 255 characters") // NEW VALIDATION
    private String name;

    @Size(max = 1000, message = "Product description cannot exceed 1000 characters") // NEW VALIDATION
    private String description;

    private String imageUrl;

    @NotNull(message = "Price is required") // NEW VALIDATION
    @Min(value = 0, message = "Price must be positive or zero") // NEW VALIDATION
    private double price;

    @NotNull(message = "Quantity is required") // NEW VALIDATION
    @Min(value = 0, message = "Quantity must be positive or zero") // NEW VALIDATION
    private int quantity;
}