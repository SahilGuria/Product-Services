package com.example.productservice.controllers;

import com.example.productservice.dtos.CreateProductRequestDto;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ProductController {


    private ProductService productService;
    private RestTemplate restTemplate;

    public ProductController(@Qualifier("selfProductService") ProductService productService, RestTemplate restTemplate){
        this.productService = productService;
        this.restTemplate = restTemplate;
    }


    @PostMapping("/products")
    //format of adding
    //{
    //    "title" : "asdf",
    //    "description":"asdf",
    //    "price":"1",
    //   "image":"asdf",
    //    "category":"Sahil"
    //}
    public Product createProduct(@RequestBody CreateProductRequestDto request){
        return productService.createProduct(
                request.getTitle(),
                request.getImage(),
                request.getDescription(),
                request.getCategory(),
                request.getPrice()
        );
    }

    @GetMapping("/products/{id}")
    public Product getProductDetails(@PathVariable("id") Long productId){
        return productService.getSingleProduct(productId);
    }
    @GetMapping("/products/category")
    public List<Product> getProductByCategory(@RequestParam String category){
        return productService.getProductsByCategory(category);
    }

    @DeleteMapping("/products/{id}")
    public String deleteProductById(@PathVariable("id") Long productId){
        productService.deleteSingleProduct(productId);
       return  "Product Deleted";
    }

    @PutMapping("/products/{id}")
    public String updateProductById(@PathVariable("id") Long productId){
        productService.updateProductById(productId);
        return "Product Update";
    }

    @GetMapping("/products")
    public List<Product> getAllProduct(){
      return  productService.getProducts();
    }
    @GetMapping("/products/categories")
    public List<String> getAllCategories(){
        return productService.getCategories();
    }
}
