package com.example.productsevice2.service;

import com.example.productsevice2.model.Product;
import com.example.productsevice2.repository.ProductRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {

    ProductRepository productRepository;

    @Override
    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Integer quantity) throws NotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        Product newProduct;
        if (product.isPresent()){
            newProduct = product.get();
            newProduct.setQuantity(quantity);

            productRepository.save(newProduct);
        }
        else{
            throw new NotFoundException("product not found with Id: "+ productId);
        }
        return newProduct;
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Optional<Product> getProduct(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}
