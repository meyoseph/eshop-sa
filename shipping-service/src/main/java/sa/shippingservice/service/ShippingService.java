package sa.shippingservice.service;

import sa.shippingservice.entity.ShippingAddress;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ShippingService {
    ShippingAddress addShippingAddress(ShippingAddress shippingAddress);
    Optional<ShippingAddress> getShippingAddress(Long id);
    Optional<ShippingAddress> updateShippingAddress(Long id, String address);
    void deleteShippingAddress(Long id);
    List<ShippingAddress> getAllShippingAddress();
    void syncShippingAddress(Long userId, String address);
}
