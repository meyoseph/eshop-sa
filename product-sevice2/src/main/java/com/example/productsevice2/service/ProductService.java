package com.example.productsevice2.service;


import com.example.productsevice2.model.Product;
import javassist.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ProductService {
    Product addProduct(Product product);
    Product updateProduct(Long productId, Integer Quantity) throws NotFoundException;
    void deleteProduct(Long productId);
    Optional<Product> getProduct(Long productId);
    List<Product> getAllProduct();
}
