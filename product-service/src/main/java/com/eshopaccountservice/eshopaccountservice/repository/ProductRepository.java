package com.eshopaccountservice.eshopaccountservice.repository;

import com.eshopaccountservice.eshopaccountservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
