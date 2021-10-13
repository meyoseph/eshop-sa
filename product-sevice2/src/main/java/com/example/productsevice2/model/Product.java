package com.example.productsevice2.model;

import com.example.productsevice2.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue
    private Long productId;
    private String name;
    private String vendor;
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private Category category;
}
