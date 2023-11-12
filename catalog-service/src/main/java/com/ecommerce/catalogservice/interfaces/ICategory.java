package com.ecommerce.catalogservice.interfaces;

import java.util.List;
import java.util.UUID;

import com.ecommerce.catalogservice.dto.CategoryDto;
import com.ecommerce.catalogservice.entity.Category;

public interface ICategory {

    Category getCategoryById(UUID categoryId);

    Category getCategoryBySlug(String slug);

    Category createCategory(CategoryDto categoryDto);

    List<Category> getAllCategories();

}