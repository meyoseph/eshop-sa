package com.example.productsevice2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ProductSevice2Application {

    public static void main(String[] args) {
        SpringApplication.run(ProductSevice2Application.class, args);
    }

}
