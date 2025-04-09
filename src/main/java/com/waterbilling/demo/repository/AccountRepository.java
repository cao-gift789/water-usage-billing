package com.waterbilling.demo.repository;

import com.waterbilling.demo.model.Account;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);
    List<Account> findByUsernameAndPassword(String username,String password);
}
