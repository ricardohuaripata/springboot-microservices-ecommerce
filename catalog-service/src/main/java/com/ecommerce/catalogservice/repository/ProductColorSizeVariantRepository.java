package com.ecommerce.catalogservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.catalogservice.entity.ProductColorSizeVariant;

@Repository
public interface ProductColorSizeVariantRepository extends JpaRepository<ProductColorSizeVariant, UUID> {

}
