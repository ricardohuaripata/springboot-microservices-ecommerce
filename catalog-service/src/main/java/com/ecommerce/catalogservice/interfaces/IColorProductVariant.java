package com.ecommerce.catalogservice.interfaces;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.ecommerce.catalogservice.dto.ColorProductVariantDto;
import com.ecommerce.catalogservice.entity.Category;
import com.ecommerce.catalogservice.entity.ColorProductVariant;
import com.ecommerce.catalogservice.entity.Product;

public interface IColorProductVariant {
    ColorProductVariant getColorProductVariantById(UUID colorProductVariantId);

    List<ColorProductVariant> getColorProductVariantsByProduct(Product product);

    Page<ColorProductVariant> getColorProductVariantsByProductCategoryPaginate(Category category, Integer page, Integer size);

    ColorProductVariant createColorProductVariant(ColorProductVariantDto colorProductVariantDto);
}
