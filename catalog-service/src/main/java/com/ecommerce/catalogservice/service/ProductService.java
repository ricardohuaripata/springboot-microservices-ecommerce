package com.ecommerce.catalogservice.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.catalogservice.dto.ProductDto;
import com.ecommerce.catalogservice.entity.Category;
import com.ecommerce.catalogservice.entity.Product;
import com.ecommerce.catalogservice.exceptions.ProductNotFoundException;
import com.ecommerce.catalogservice.exceptions.SlugExistsException;
import com.ecommerce.catalogservice.interfaces.IProduct;
import com.ecommerce.catalogservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService implements IProduct {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Override
    public Product getProductById(UUID productId) {
        return productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);

    }

    @Override
    public Product getProductBySlug(String slug) {
        return productRepository.findBySlug(slug).orElseThrow(ProductNotFoundException::new);

    }

    @Override
    public Product createProduct(ProductDto productDto) {
        try {
            getProductBySlug(productDto.getSlug());
            // Si no se lanzó la excepción ProductNotFoundException, significa que el slug
            // ya existe
            throw new SlugExistsException();

        } catch (ProductNotFoundException e) {

            Category category = categoryService.getCategoryById(productDto.getCategoryId());

            Product newProduct = new Product();

            newProduct.setCategory(category);
            newProduct.setTitle(productDto.getTitle());
            newProduct.setSlug(productDto.getSlug());
            newProduct.setDescription(productDto.getDescription());
            Date currentDate = new Date();
            newProduct.setDateCreated(currentDate);
            newProduct.setDateLastModified(currentDate);

            return productRepository.save(newProduct);
        }
    }

    @Override
    public Page<Product> getAllProductsPaginate(Integer page, Integer size) {
        return productRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dateCreated")));

    }

    @Override
    public Page<Product> getProductsByCategoryPaginate(Category category, Integer page, Integer size) {
        return productRepository.findProductsByCategory(category, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dateCreated")));
    }

}
