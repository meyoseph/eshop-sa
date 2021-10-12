package sa.shippingservice.service;

import sa.shippingservice.entity.ShippingAddress;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sa.shippingservice.repository.ShippingRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShippingServiceImp implements ShippingService{

    private ShippingRepository repository;

    @Override
    public ShippingAddress addShippingAddress(ShippingAddress shippingAddress) {
        return repository.save(shippingAddress);
    }

    @Override
    public Optional<ShippingAddress> getShippingAddress(Long id){
        return repository.findById(id);
    }

    @Override
    public Optional<ShippingAddress> updateShippingAddress(Long id, String address) {
        Optional<ShippingAddress> shippingAddress = repository.findById(id);
        if(shippingAddress.isPresent()){
            shippingAddress.get().setAddress(address);
        }
        return shippingAddress;
    }

    @Override
    public void deleteShippingAddress(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<ShippingAddress> getAllShippingAddress() {
        return repository.findAll();
    }

    @Override
    public void syncShippingAddress(Long userId, String address){
        ShippingAddress address1 = new ShippingAddress();
        address1.setAddress(address);
        address1.setUserId(userId);
        repository.save(address1);
    }
}
