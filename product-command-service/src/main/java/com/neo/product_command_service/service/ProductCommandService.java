package com.neo.product_command_service.service;

import com.neo.product_command_service.dto.ProductEvent;
import com.neo.product_command_service.entity.Product;
import com.neo.product_command_service.repository.ProductRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandService {

    private KafkaTemplate<String,Object> kafkaTemplate;

    private ProductRepository repository;

    public ProductCommandService(KafkaTemplate<String, Object> kafkaTemplate, ProductRepository repository) {
        this.kafkaTemplate = kafkaTemplate;
        this.repository = repository;
    }

    public Product createProduct(Product product){
        Product productSaved= repository.save(product);
        ProductEvent event=new ProductEvent("CreateProduct",productSaved);
        kafkaTemplate.send("product-event-topic",event);
        return productSaved;
    }

    public Product updateProduct(long id,Product product){
        Product existingProduct= repository.findById(id).orElseThrow(()->new RuntimeException("Resource not found"));
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        Product savedProduct= repository.save(existingProduct);
        ProductEvent event=new ProductEvent("UpdateProduct",savedProduct);
        kafkaTemplate.send("product-event-topic",event);
        return savedProduct;
    }
}
