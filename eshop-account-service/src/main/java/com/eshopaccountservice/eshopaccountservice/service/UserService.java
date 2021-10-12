package com.eshopaccountservice.eshopaccountservice.service;

import com.eshopaccountservice.eshopaccountservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    User registerNewUserAAccount(User accountDto);
    Object validateToken(String token);
}
