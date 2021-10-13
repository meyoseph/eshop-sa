package miu.edu.sa.orderservice.proxy;

import miu.edu.sa.orderservice.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@FeignClient(name = "product-service2")
public interface productProxy {

    @GetMapping("/product/find/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long productId);

    @PutMapping("/product/update/{id}/{quantity}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @PathVariable Integer quantity);

}
