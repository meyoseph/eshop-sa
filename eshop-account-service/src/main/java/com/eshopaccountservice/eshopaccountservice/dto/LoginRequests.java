package com.eshopaccountservice.eshopaccountservice.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class LoginRequests {
    private String username;
    private String password;
}