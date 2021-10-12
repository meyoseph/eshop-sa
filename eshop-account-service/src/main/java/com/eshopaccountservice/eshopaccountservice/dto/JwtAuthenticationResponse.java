package com.eshopaccountservice.eshopaccountservice.dto;


import lombok.Getter;
import com.eshopaccountservice.eshopaccountservice.security.UserPrincipal;

@Getter
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private UserPrincipal user ;

    public JwtAuthenticationResponse(String accessToken, UserPrincipal userDetails) {
        this.accessToken = accessToken;
        this.user = userDetails;
    }

}