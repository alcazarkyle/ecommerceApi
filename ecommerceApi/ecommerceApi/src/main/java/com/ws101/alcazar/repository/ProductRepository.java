package com.ws101.alcazar.ecommerceapi.repository;

import com.ws101.alcazar.ecommerceapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}