package com.example.productservice.services;

import com.example.productservice.models.Product;
import java.util.List;
public interface ProductService {
    Product getSingleProduct(Long productId);

    void deleteSingleProduct(Long productId);
    List<Product> getProducts();

    List<String> getCategories();

    void updateProductById(Long productId);

    List<Product> getProductsByCategory(String category);

    Product createProduct( String title,
                           String image,
                           String description,
                           String category,
                           double price);
}
