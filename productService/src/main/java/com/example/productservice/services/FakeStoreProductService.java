package com.example.productservice.services;
import java.net.URI;
import java.util.*;
import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.models.Product;
import com.mashape.unirest.http.Unirest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {


    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        FakeStoreProductDto fakeStoreProduct =  restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+ productId,
                FakeStoreProductDto.class
        );
        return fakeStoreProduct.toproduct();
    }

    @Override
    public void deleteSingleProduct(Long productId) {
        restTemplate.delete("https://fakestoreapi.com/products/"+productId);
    }

    @Override
    public List<Product> getProducts() {
//        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products" , FakeStoreProductDto[].class);
//
//        FakeStoreProductDto[] products = responseEntity.getBody();
//        if (products != null) {
//            List<Product> productList = new ArrayList<>();
//            for(int i =0; i< products.length; i++){
//                productList.add(products[i].toproduct());
//            }
//            return productList;
//        } else {
//            return Collections.emptyList();
//        }
        FakeStoreProductDto[] fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products",FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProducts:fakeStoreProductDto){
            products.add(fakeStoreProducts.toproduct());
        }
        return products;
    }

    @Override
    public List<String> getCategories() {
        String[] fakeStoreProduct = restTemplate.getForEntity("https://fakestoreapi.com/products/categories",String[].class).getBody();
        if (fakeStoreProduct != null) {
            List<String> productList = new ArrayList<>();
            for(int i =0; i< fakeStoreProduct.length; i++){
                productList.add(fakeStoreProduct[i]);
            }
         return productList;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void updateProductById(Long productId) {
        Product product = getSingleProduct(productId);
//        restTemplate.put("https://fakestoreapi.com/products/"+productId , product);
        Unirest.put("https://fakestoreapi.com/products/"+productId).field("product",product);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        String url  = "https://fakestoreapi.com/products/category/"+category;
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(url,FakeStoreProductDto[].class);
        if (fakeStoreProductDtos != null) {
            List<Product> productList = new ArrayList<>();
            for(int i =0; i< fakeStoreProductDtos.length; i++){
                productList.add(fakeStoreProductDtos[i].toproduct());
            }
            return productList;

        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public Product createProduct(String title, String image, String description, String category, double price){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        FakeStoreProductDto response = restTemplate.postForObject(
                "https://fakestoreapi.com/products",//url
                fakeStoreProductDto,//request body
                FakeStoreProductDto.class//data type of response
        );
        return response.toproduct();
    }
}
