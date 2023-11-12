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
public class CategoryDto {
    @NotEmpty
    @Size(max = 64)
    private String name;

    @NotEmpty
    @Size(max = 64)
    @Pattern(regexp = "^[a-z0-9-]+$", message = "Invalid slug, only lowercase letters, numbers and hyphens are allowed") // Expresión regular para permitir letras minúsculas, números y guiones
    private String slug;

    @NotEmpty
    @Size(max = 1024)
    private String description;
}
