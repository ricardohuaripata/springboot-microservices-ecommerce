package com.ecommerce.catalogservice.interfaces;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.ecommerce.catalogservice.dto.ProductDto;
import com.ecommerce.catalogservice.entity.Category;
import com.ecommerce.catalogservice.entity.Product;

public interface IProduct {

    Product getProductById(UUID productId);

    Product getProductBySlug(String slug);

    Product createProduct(ProductDto productDto);

    Page<Product> getAllProductsPaginate(Integer page, Integer size);

    Page<Product> getProductsByCategoryPaginate(Category category, Integer page, Integer size);

}
