package com.waterbilling.demo.repository;

import com.waterbilling.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
//    Role findByRoleName(String roleName);
//
//    Optional<Role> findByName(String user);
}
