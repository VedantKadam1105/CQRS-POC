package com.neo.product_command_service.service;

import com.neo.product_command_service.entity.Product;
import com.neo.product_command_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandService {

    private ProductRepository repository;

    public ProductCommandService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product createProduct(Product product){
        return repository.save(product);
    }

    public Product updateProduct(long id,Product product){
        Product existingProduct= repository.findById(id).orElseThrow(()->new RuntimeException("Resource not found"));
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        return repository.save(existingProduct);
    }
}
