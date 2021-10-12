package com.eshopaccountservice.eshopaccountservice.controller;

import com.eshopaccountservice.eshopaccountservice.dto.JwtAuthenticationResponse;
import com.eshopaccountservice.eshopaccountservice.dto.LoginRequests;
import com.eshopaccountservice.eshopaccountservice.entity.User;
import com.eshopaccountservice.eshopaccountservice.proxy.ShippingAddressServiceProxy;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.eshopaccountservice.eshopaccountservice.security.JwtTokenProvider;
import com.eshopaccountservice.eshopaccountservice.security.UserPrincipal;
import com.eshopaccountservice.eshopaccountservice.service.UserServiceImp;

@RestController
@CrossOrigin
@RequestMapping("/api/auth/")
@AllArgsConstructor
public class AuthenticationController {
    private ShippingAddressServiceProxy proxy;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider tokenProvider;
    private UserServiceImp userService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequests loginRequest) {
        System.out.println("Logging");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, (UserPrincipal) authentication.getPrincipal()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        User newUser = userService.registerNewUserAAccount(user);
        proxy.addShippingAddress(newUser.getId(), user.getAddress());
        return ResponseEntity.ok(UserPrincipal.create(newUser));
    }

    @PostMapping("/validateToken")
    public ResponseEntity<?> signIn(@RequestParam String token){
        return ResponseEntity.ok(userService.validateToken(token));
    }
}