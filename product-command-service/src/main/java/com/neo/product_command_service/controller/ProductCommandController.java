package com.neo.product_command_service.controller;

import com.neo.product_command_service.entity.Product;
import com.neo.product_command_service.service.ProductCommandService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

    private ProductCommandService productCommandService;

    public ProductCommandController(ProductCommandService productCommandService) {
        this.productCommandService = productCommandService;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productCommandService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable long id,@RequestBody Product product){
        return productCommandService.updateProduct(id,product);
    }
}
