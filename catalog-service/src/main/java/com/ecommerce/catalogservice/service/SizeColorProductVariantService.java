package com.ecommerce.catalogservice.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.catalogservice.dto.SizeColorProductVariantDto;
import com.ecommerce.catalogservice.entity.ColorProductVariant;
import com.ecommerce.catalogservice.entity.SizeColorProductVariant;
import com.ecommerce.catalogservice.exceptions.SizeColorProductVariantExistsException;
import com.ecommerce.catalogservice.exceptions.SizeColorProductVariantNotFoundException;
import com.ecommerce.catalogservice.interfaces.ISizeColorProductVariant;
import com.ecommerce.catalogservice.repository.SizeColorProductVariantRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SizeColorProductVariantService implements ISizeColorProductVariant {
    private final SizeColorProductVariantRepository sizeColorProductVariantRepository;
    private final ColorProductVariantService colorProductVariantService;

    @Override
    public SizeColorProductVariant getSizeColorProductVariantById(UUID sizeColorProductVariantId) {
        return sizeColorProductVariantRepository.findById(sizeColorProductVariantId)
                .orElseThrow(SizeColorProductVariantNotFoundException::new);
    }

    @Override
    public List<SizeColorProductVariant> getSizeColorProductVariantsByColorProductVariant(
            ColorProductVariant colorProductVariant) {
        return sizeColorProductVariantRepository.findSizeColorProductVariantsByColorProductVariant(colorProductVariant);

    }

    @Override
    public SizeColorProductVariant createSizeColorProductVariant(
            SizeColorProductVariantDto sizeColorProductVariantDto) {

        ColorProductVariant colorProductVariant = colorProductVariantService
                .getColorProductVariantById(sizeColorProductVariantDto.getColorProductVariantId());

        // Verificar si ya existe una variación para el productoColor y la talla dada
        if (sizeColorProductVariantRepository.existsByColorProductVariantAndSize(colorProductVariant,
                sizeColorProductVariantDto.getSize())) {
            // Manejar el caso en el que ya existe una variación
            throw new SizeColorProductVariantExistsException();
        }

        SizeColorProductVariant newSizeColorProductVariant = new SizeColorProductVariant();

        newSizeColorProductVariant.setColorProductVariant(colorProductVariant);
        newSizeColorProductVariant.setSize(sizeColorProductVariantDto.getSize());
        newSizeColorProductVariant.setStock(sizeColorProductVariantDto.getStock());
        Date currentDate = new Date();
        newSizeColorProductVariant.setDateCreated(currentDate);
        newSizeColorProductVariant.setDateLastModified(currentDate);

        return sizeColorProductVariantRepository.save(newSizeColorProductVariant);
    }

}
