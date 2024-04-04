package com.example.productservice.dtos;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private  Long id;
    private  String title;
    private  String image;
    private  String description;
    private String category;
    private double price;

    public Product toproduct(){
        Product product= new Product();
        product.setId(id);
        product.setDescription(description);
        product.setTitle(title);
        product.setPrice(price);
        product.setImageUrl(image);


        Category productCategory = new Category();
        productCategory.setTitle(category);
        product.setCategory(productCategory);
        return product;
    }
}
