package com.ecommerce.catalogservice.interfaces;

import java.util.List;
import java.util.UUID;

import com.ecommerce.catalogservice.dto.SizeColorProductVariantDto;
import com.ecommerce.catalogservice.entity.ColorProductVariant;
import com.ecommerce.catalogservice.entity.SizeColorProductVariant;

public interface ISizeColorProductVariant {
    SizeColorProductVariant getSizeColorProductVariantById(UUID sizeColorProductVariantId);

    List<SizeColorProductVariant> getSizeColorProductVariantsByColorProductVariant(
            ColorProductVariant colorProductVariant);

    SizeColorProductVariant createSizeColorProductVariant(SizeColorProductVariantDto sizeColorProductVariantDto);

}
