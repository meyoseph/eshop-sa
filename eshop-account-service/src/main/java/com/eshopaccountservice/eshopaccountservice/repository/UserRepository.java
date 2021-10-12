package com.eshopaccountservice.eshopaccountservice.repository;

import com.eshopaccountservice.eshopaccountservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User getUserByUsername(@Param("email") String email);
}
