package com.eshopaccountservice.eshopaccountservice.service;

import com.eshopaccountservice.eshopaccountservice.entity.User;
import com.eshopaccountservice.eshopaccountservice.security.CustomUserDetailService;
import com.eshopaccountservice.eshopaccountservice.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.eshopaccountservice.eshopaccountservice.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    private CustomUserDetailService customUserDetailsService;
    private JwtTokenProvider tokenProvider;
    private PasswordEncoder passwordEncoder;
    private UserRepository repository;

    @Override
    public User registerNewUserAAccount(User accountDto) {
        accountDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        return repository.save(accountDto);
    }

    @Override
    public Object validateToken(String token){
        if(tokenProvider.validateToken(token)){
            Long userId = tokenProvider.getUserIdFromJWT(token);
            UserDetails user = customUserDetailsService.loadUserById(userId);
            return user;
        }
        return null;
    }
}
