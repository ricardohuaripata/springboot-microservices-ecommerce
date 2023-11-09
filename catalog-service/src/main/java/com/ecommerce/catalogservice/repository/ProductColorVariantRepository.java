package com.ecommerce.catalogservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.catalogservice.entity.ProductColorVariant;

@Repository
public interface ProductColorVariantRepository extends JpaRepository<ProductColorVariant, UUID> {

}
