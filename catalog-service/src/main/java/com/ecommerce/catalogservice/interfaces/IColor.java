package com.ecommerce.catalogservice.interfaces;

import java.util.List;
import java.util.UUID;

import com.ecommerce.catalogservice.dto.ColorDto;
import com.ecommerce.catalogservice.entity.Color;

public interface IColor {
    Color getColorById(UUID colorId);

    Color getColorByHexcode(String hexcode);

    Color createColor(ColorDto colorDto);

    List<Color> getAllColors();
}
