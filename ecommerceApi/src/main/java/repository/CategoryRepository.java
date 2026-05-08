package com.ws101.alcazar.ecommerceApi.repository;

import com.ws101.alcazar.ecommerceApi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Category entity.
 * Handles database operations for Categories.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
Compose
