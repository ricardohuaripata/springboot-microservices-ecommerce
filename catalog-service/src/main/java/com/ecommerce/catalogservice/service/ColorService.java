package com.ecommerce.catalogservice.service;

import com.ecommerce.catalogservice.interfaces.IColor;
import com.ecommerce.catalogservice.repository.ColorRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.catalogservice.dto.ColorDto;
import com.ecommerce.catalogservice.entity.Color;
import com.ecommerce.catalogservice.exceptions.ColorNotFoundException;
import com.ecommerce.catalogservice.exceptions.HexcodeExistsException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ColorService implements IColor {
    private final ColorRepository colorRepository;

    @Override
    public Color getColorById(UUID colorId) {
        return colorRepository.findById(colorId).orElseThrow(ColorNotFoundException::new);

    }

    @Override
    public Color getColorByHexcode(String hexcode) {
        return colorRepository.findByHexCode(hexcode).orElseThrow(ColorNotFoundException::new);

    }

    @Override
    public Color createColor(ColorDto colorDto) {
        try {
            getColorByHexcode(colorDto.getHexcode());

            // Si no se lanzó la excepción, significa que el color ya existe
            throw new HexcodeExistsException();

        } catch (ColorNotFoundException e) {
            Color newColor = new Color();

            newColor.setName(colorDto.getName());
            newColor.setHexCode(colorDto.getHexcode());
            Date currentDate = new Date();
            newColor.setDateCreated(currentDate);
            newColor.setDateLastModified(currentDate);

            return colorRepository.save(newColor);
        }
    }

    @Override
    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

}
