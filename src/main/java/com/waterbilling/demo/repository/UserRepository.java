package com.waterbilling.demo.repository;

import com.waterbilling.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByIdentityNumber(String identityNumber);
    Optional<User> findByPhoneNumber(String phoneNumber);
}
