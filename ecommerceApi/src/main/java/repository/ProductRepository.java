package com.ws101.alcazar.ecommerceApi.repository;

import com.ws101.alcazar.ecommerceApi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository interface for Product entity.
 * Handles database operations for Products.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find products by category name (Method Naming Convention)
    List<Product> findByCategoryName(String categoryName);

    // Custom query to find products within a price range (JPQL)
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    List<Product> findByPriceBetween(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
}