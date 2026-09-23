package com.neo.product_query_service.controller;

import com.neo.product_query_service.entity.Product;
import com.neo.product_query_service.service.ProductQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    private ProductQueryService productQueryService;

    public ProductQueryController(ProductQueryService productQueryService) {
        this.productQueryService = productQueryService;
    }
    @GetMapping
    public List<Product> getAllProducts(){
        return productQueryService.getAllProducts();
    }
}
