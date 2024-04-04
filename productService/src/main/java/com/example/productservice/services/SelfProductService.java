package com.example.productservice.services;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("selfProductService")
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository,CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long productId) {

        return productRepository.findById(productId);
    }

    @Override
    public void deleteSingleProduct(Long productId) {

    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<String> getCategories() {
        return null;
    }

    @Override
    public void updateProductById(Long productId) {

    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return null;
    }

    @Override
    public Product createProduct(String title, String image, String description, String category, double price) {
        Product product =  new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setImageUrl(image);
        product.setPrice(price);
        Category categoryFromDataBase = categoryRepository.findByTitle(category) ;
        if(categoryFromDataBase==null){

            Category newCategory  = new Category();
            newCategory.setTitle(category);
            categoryFromDataBase = newCategory;
         }
        product.setCategory(categoryFromDataBase);

        return productRepository.save(product);
    }
}
