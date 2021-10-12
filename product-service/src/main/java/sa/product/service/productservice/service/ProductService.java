package sa.product.service.productservice.service;

import com.eshopaccountservice.eshopaccountservice.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ProductService {
    Product addProduct(Product product);
    Optional<Product> updateProduct(Long productId, String name);
    void deleteProduct(Long productId);
    Optional<Product> getProduct(Long productId);
    List<Product> getAllProduct();
}
