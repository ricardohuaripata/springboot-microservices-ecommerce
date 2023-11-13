package com.ecommerce.catalogservice.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.catalogservice.dto.ColorProductVariantDto;
import com.ecommerce.catalogservice.entity.Category;
import com.ecommerce.catalogservice.entity.Color;
import com.ecommerce.catalogservice.entity.ColorProductVariant;
import com.ecommerce.catalogservice.entity.Product;
import com.ecommerce.catalogservice.exceptions.ColorProductVariantExistsException;
import com.ecommerce.catalogservice.exceptions.ColorProductVariantNotFoundException;
import com.ecommerce.catalogservice.interfaces.IColorProductVariant;
import com.ecommerce.catalogservice.repository.ColorProductVariantRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ColorProductVariantService implements IColorProductVariant {

    private final ColorProductVariantRepository colorProductVariantRepository;
    private final ProductService productService;
    private final ColorService colorService;

    @Override
    public ColorProductVariant getColorProductVariantById(UUID colorProductVariantId) {
        return colorProductVariantRepository.findById(colorProductVariantId)
                .orElseThrow(ColorProductVariantNotFoundException::new);
    }

    @Override
    public List<ColorProductVariant> getColorProductVariantsByProduct(Product product) {
        return colorProductVariantRepository.findColorProductVariantsByProduct(product);
    }

    @Override
    public ColorProductVariant createColorProductVariant(ColorProductVariantDto colorProductVariantDto) {

        Product product = productService.getProductById(colorProductVariantDto.getProductId());
        Color color = colorService.getColorById(colorProductVariantDto.getColorId());

        // Verificar si ya existe una variación para el producto y color dados
        if (colorProductVariantRepository.existsByProductAndColor(product, color)) {
            // Manejar el caso en el que ya existe una variación
            throw new ColorProductVariantExistsException();
        }

        ColorProductVariant newColorProductVariant = new ColorProductVariant();

        newColorProductVariant.setProduct(product);
        newColorProductVariant.setColor(color);
        newColorProductVariant.setBasePrice(colorProductVariantDto.getBasePrice());
        newColorProductVariant.setFinalPrice(colorProductVariantDto.getFinalPrice());
        newColorProductVariant.setFrontImageUrl(colorProductVariantDto.getFrontImageUrl());
        newColorProductVariant.setBackImageUrl(colorProductVariantDto.getBackImageUrl());
        Date currentDate = new Date();
        newColorProductVariant.setDateCreated(currentDate);
        newColorProductVariant.setDateLastModified(currentDate);

        return colorProductVariantRepository.save(newColorProductVariant);
    }

    @Override
    public Page<ColorProductVariant> getColorProductVariantsByProductCategoryPaginate(Category category, Integer page, Integer size) {
        return colorProductVariantRepository.findColorProductVariantsByProduct_Category(category, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dateCreated")));
    }

}
