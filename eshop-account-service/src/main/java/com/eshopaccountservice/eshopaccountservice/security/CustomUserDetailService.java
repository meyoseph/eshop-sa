package com.eshopaccountservice.eshopaccountservice.security;

import com.eshopaccountservice.eshopaccountservice.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.eshopaccountservice.eshopaccountservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
    private UserRepository repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        // Let people login with either username or email
        User user = repository.getUserByUsername(username);
        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = repository.findById(id).orElseThrow(
                RuntimeException::new
        );

        return UserPrincipal.create(user);
    }
}
