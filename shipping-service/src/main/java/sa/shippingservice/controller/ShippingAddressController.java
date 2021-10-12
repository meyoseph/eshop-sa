package sa.shippingservice.controller;

import sa.shippingservice.entity.ShippingAddress;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sa.shippingservice.service.ShippingServiceImp;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shipping/")
@AllArgsConstructor
public class ShippingAddressController {
    private ShippingServiceImp shippingServiceImp;

    @GetMapping("/all")
    public ResponseEntity<List<ShippingAddress>> getAllShippingAddress(){
        List<ShippingAddress> shippingAddresses = shippingServiceImp.getAllShippingAddress();
        return new ResponseEntity<>(shippingAddresses, HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<ShippingAddress> getShippingAddress(@PathVariable Long id){
        ShippingAddress shippingAddress = shippingServiceImp.getShippingAddress(id).get();
        return new ResponseEntity<>(shippingAddress, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ShippingAddress> addShippingAddress(@RequestBody ShippingAddress shipingAddress){
        ShippingAddress newShippingAddress = shippingServiceImp.addShippingAddress(shipingAddress);
        return new ResponseEntity<>(newShippingAddress, HttpStatus.CREATED);
    }

    @PutMapping("/find/{id}/{address}")
    public ResponseEntity<?> updateShippingAddress(@PathVariable Long id, @PathVariable String address){
        Optional<ShippingAddress> shippingAddress1 = shippingServiceImp.getShippingAddress(id);
        if (shippingAddress1.isPresent()){
            ShippingAddress sa = shippingServiceImp.updateShippingAddress(id, address).get();
            return new ResponseEntity<>(sa, HttpStatus.OK);
        }

        return new ResponseEntity<>("record is missing", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteShippingAddress(@PathVariable Long id){
        shippingServiceImp.deleteShippingAddress(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/syncaddress/{userId}/{address}")
    public void syncShippingAddress(@PathVariable Long userId, @PathVariable String address){
        shippingServiceImp.syncShippingAddress(userId,address);
    }
}
