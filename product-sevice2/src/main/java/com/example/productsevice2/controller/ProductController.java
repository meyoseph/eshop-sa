package com.example.productsevice2.controller;

import com.example.productsevice2.model.Product;
import com.example.productsevice2.service.ProductServiceImp;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/update/{id}/{quantity}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @PathVariable Integer quantity) throws NotFoundException {
        Optional<Product> updatedProduct = Optional.ofNullable(productService.updateProduct(id, quantity));
        return new ResponseEntity<>(updatedProduct.get(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
