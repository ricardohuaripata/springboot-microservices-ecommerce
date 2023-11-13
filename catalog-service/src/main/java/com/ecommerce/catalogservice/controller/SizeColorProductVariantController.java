package com.ecommerce.catalogservice.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.catalogservice.dto.SizeColorProductVariantDto;
import com.ecommerce.catalogservice.entity.ColorProductVariant;
import com.ecommerce.catalogservice.entity.SizeColorProductVariant;
import com.ecommerce.catalogservice.service.ColorProductVariantService;
import com.ecommerce.catalogservice.service.SizeColorProductVariantService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/size-color-product-variant")
@RequiredArgsConstructor
public class SizeColorProductVariantController {
    private final SizeColorProductVariantService sizeColorProductVariantService;
    private final ColorProductVariantService colorProductVariantService;

    @GetMapping("/{sizeColorProductVariantId}")
    public ResponseEntity<?> getSizeColorProductVariantById(
            @PathVariable("sizeColorProductVariantId") UUID sizeColorProductVariantId) {

        SizeColorProductVariant sizeColorProductVariant = sizeColorProductVariantService
                .getSizeColorProductVariantById(sizeColorProductVariantId);

        return new ResponseEntity<>(sizeColorProductVariant, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createSizeColorProductVariant(
            @RequestBody @Valid SizeColorProductVariantDto sizeColorProductVariantDto) {

        SizeColorProductVariant createdSizeColorProductVariant = sizeColorProductVariantService
                .createSizeColorProductVariant(sizeColorProductVariantDto);

        return new ResponseEntity<>(createdSizeColorProductVariant, HttpStatus.CREATED);
    }

    @GetMapping("/colorProductVariant/{colorProductVariantId}")
    public ResponseEntity<?> getSizeColorProductVariantsByColorProductVariant(
            @PathVariable("colorProductVariantId") UUID colorProductVariantId) {

        ColorProductVariant colorProductVariant = colorProductVariantService
                .getColorProductVariantById(colorProductVariantId);

        List<SizeColorProductVariant> sizeColorProductVariants = sizeColorProductVariantService
                .getSizeColorProductVariantsByColorProductVariant(colorProductVariant);

        return new ResponseEntity<>(sizeColorProductVariants, HttpStatus.OK);
    }
}
