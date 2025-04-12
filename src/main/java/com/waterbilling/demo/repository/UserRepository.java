package com.waterbilling.demo.repository;

import com.waterbilling.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByIdentityNumber(String identityNumber);
    Optional<User> findByPhoneNumber(String phoneNumber);

    @Procedure(procedureName = "RegisterOrUpdateUserWithAccount", outputParameterName = "p_message")
    String registerOrUpdateUserWithAccount(
            @Param("p_identity_number") String identityNumber,
            @Param("p_email") String email,
            @Param("p_phone_number") String phoneNumber,
            @Param("p_full_name") String fullName,
            @Param("p_password") String password,
            @Param("p_username") String username
    );
}
