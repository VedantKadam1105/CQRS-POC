package com.neo.product_query_service.service;

import com.neo.product_query_service.dto.ProductEvent;
import com.neo.product_query_service.entity.Product;
import com.neo.product_query_service.repository.ProductRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductQueryService {
    private ProductRepository productRepository;

    public ProductQueryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @KafkaListener(topics = "product-event-topic",groupId = "product-event-group")
    public void processProductEvents(ProductEvent productEvent){
        Product product=productEvent.getProduct();
        if(productEvent.getEventType().equals("CreateProduct")){
            productRepository.save(product);
        } else if (productEvent.getEventType().equals("UpdateProduct")) {
            Product existingProduct=productRepository.findById(product.getId()).get();
            existingProduct.setDescription(product.getDescription());
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            productRepository.save(existingProduct);
        }
    }
}
