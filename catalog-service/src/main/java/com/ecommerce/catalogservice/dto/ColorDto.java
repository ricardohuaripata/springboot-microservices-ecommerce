package com.ecommerce.catalogservice.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColorDto {
    @NotEmpty
    @Size(max = 64)
    private String name;

    @NotEmpty
    @Size(max = 6) // Se espera un máximo de 6 caracteres hexadecimales
    @Pattern(regexp = "^[A-Fa-f0-9]{6}$", message = "Invalid hexcode, must contain 6 valid hexadecimal characters")
    private String hexcode;

}
