package com.eshopaccountservice.eshopaccountservice.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.eshopaccountservice.eshopaccountservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class UserPrincipal implements UserDetails {
    private Long id;

    private String name;

    @JsonIgnore
    private String username;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private User user;

    public UserPrincipal(User user) {
        this.id = user.getId();
        this.name = user.getFirstName() + " " + user.getLastName();
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.user = user;
    }

    public static UserPrincipal create(User user) {
        return new UserPrincipal(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
