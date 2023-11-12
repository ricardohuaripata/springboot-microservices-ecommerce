package com.ecommerce.catalogservice.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.catalogservice.dto.CategoryDto;
import com.ecommerce.catalogservice.entity.Category;
import com.ecommerce.catalogservice.exceptions.CategoryNotFoundException;
import com.ecommerce.catalogservice.exceptions.SlugExistsException;
import com.ecommerce.catalogservice.interfaces.ICategory;
import com.ecommerce.catalogservice.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService implements ICategory {
    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(UUID categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);

    }

    @Override
    public Category getCategoryBySlug(String slug) {
        return categoryRepository.findBySlug(slug).orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public Category createCategory(CategoryDto categoryDto) {

        try {
            getCategoryBySlug(categoryDto.getSlug());

            // Si no se lanzó la excepción, significa que la categoria ya existe
            throw new SlugExistsException();

        } catch (CategoryNotFoundException e) {
            Category newCategory = new Category();

            newCategory.setName(categoryDto.getName());
            newCategory.setDescription(categoryDto.getDescription());
            newCategory.setSlug(categoryDto.getSlug());
            Date currentDate = new Date();
            newCategory.setDateCreated(currentDate);
            newCategory.setDateLastModified(currentDate);

            return categoryRepository.save(newCategory);
        }

    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}
