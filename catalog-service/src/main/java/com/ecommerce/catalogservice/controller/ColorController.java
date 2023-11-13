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

import com.ecommerce.catalogservice.dto.CategoryDto;
import com.ecommerce.catalogservice.dto.ColorDto;
import com.ecommerce.catalogservice.entity.Category;
import com.ecommerce.catalogservice.entity.Color;
import com.ecommerce.catalogservice.service.ColorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/color")
@RequiredArgsConstructor
public class ColorController {
    private final ColorService colorService;

    @GetMapping()
    public ResponseEntity<?> getAllCategories() {
        List<Color> colorList = colorService.getAllColors();
        return new ResponseEntity<>(colorList, HttpStatus.OK);
    }

    @GetMapping("/{colorId}")
    public ResponseEntity<?> getColorById(@PathVariable("colorId") UUID colorId) {
        Color colorToFind = colorService.getColorById(colorId);
        return new ResponseEntity<>(colorToFind, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createColor(@RequestBody @Valid ColorDto colorDto) {
        Color createdColor = colorService.createColor(colorDto);
        return new ResponseEntity<>(createdColor, HttpStatus.CREATED);
    }
}
