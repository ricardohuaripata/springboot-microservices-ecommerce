package com.ecommerce.catalogservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.catalogservice.entity.Category;
import com.ecommerce.catalogservice.entity.Color;
import com.ecommerce.catalogservice.entity.ColorProductVariant;
import com.ecommerce.catalogservice.entity.Product;

@Repository
public interface ColorProductVariantRepository extends JpaRepository<ColorProductVariant, UUID> {
    List<ColorProductVariant> findColorProductVariantsByProduct(Product product);
    boolean existsByProductAndColor(Product product, Color color);
    Page<ColorProductVariant> findColorProductVariantsByProduct_Category(Category category, Pageable pageable);

}
