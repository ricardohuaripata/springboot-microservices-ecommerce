package com.ecommerce.catalogservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.catalogservice.entity.ColorProductVariant;
import com.ecommerce.catalogservice.entity.SizeColorProductVariant;

@Repository
public interface SizeColorProductVariantRepository extends JpaRepository<SizeColorProductVariant, UUID> {
    List<SizeColorProductVariant> findSizeColorProductVariantsByColorProductVariant(
            ColorProductVariant colorProductVariant);

    boolean existsByColorProductVariantAndSize(ColorProductVariant colorProductVariant, String size);

}
