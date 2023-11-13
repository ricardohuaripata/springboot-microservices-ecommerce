package com.ecommerce.catalogservice.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.catalogservice.entity.Category;
import com.ecommerce.catalogservice.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findBySlug(String slug);

    Page<Product> findProductsByCategory(Category category, Pageable pageable);

}
