package com.ecommerce.catalogservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.catalogservice.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

}
