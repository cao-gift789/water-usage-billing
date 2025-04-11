package com.waterbilling.demo.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    Integer userId;

    @Column(name = "FullName", nullable = false, length = 50)
    String fullName;

    @Column(name = "IdentityNumber", nullable = false, unique = true, length = 12)
    String identityNumber;

    @Column(name = "PhoneNumber", nullable = false, unique = true, length = 15)
    String phoneNumber;

    @Column(name = "Email", nullable = false, unique = true, length = 255)
    String email;

    @Column(name = "IsActive")
    Boolean isActive = true;

    @Column(name = "ProfilePicture", length = 255)
    String profilePicture;

    @OneToOne
    @JoinColumn(name = "AccountID", foreignKey = @ForeignKey(name = "fk_user_account"))
    Account account;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<Invoice> invoices = new HashSet<>();




    
    
}
