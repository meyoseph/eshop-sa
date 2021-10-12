package com.eshopaccountservice.eshopaccountservice.controller;

import com.eshopaccountservice.eshopaccountservice.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sa.product.service.productservice.service.ProductServiceImp;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    private ProductServiceImp productService;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProduct();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long productId){
        Optional<Product> product = productService.getProduct(productId);
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product newProduct){
        Product product = productService.addProduct(newProduct);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}/{name}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @PathVariable String name){
        Optional<Product> updatedProduct = productService.updateProduct(id, name);
        return new ResponseEntity<>(updatedProduct.get(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
