package com.eshopaccountservice.eshopaccountservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="shipping-service")
public interface ShippingAddressServiceProxy {
    @PostMapping("/api/shipping/syncaddress/{userId}/{address}")
    void addShippingAddress(@PathVariable Long userId, @PathVariable String address);
}
