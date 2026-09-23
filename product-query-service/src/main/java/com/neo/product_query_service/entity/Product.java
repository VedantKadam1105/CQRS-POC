package com.neo.product_query_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRODUCT_QUERY")
@Getter
@Setter
public class Product {

    @Id
    private Long id;
    private String name;
    private String description;
    private double price;
}
