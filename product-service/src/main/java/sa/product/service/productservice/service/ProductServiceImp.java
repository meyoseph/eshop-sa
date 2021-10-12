package sa.product.service.productservice.service;

import com.eshopaccountservice.eshopaccountservice.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.eshopaccountservice.eshopaccountservice.repository.ProductRepository;

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
    public Optional<Product> updateProduct(Long productId, String name) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()){
            product.get().setName(name);
        }
        return product;
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
