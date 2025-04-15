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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@Entity
@Table(name = "User", uniqueConstraints = {
        @UniqueConstraint(name = "uk_user_email", columnNames = {"Email"}),
        @UniqueConstraint(name = "uk_user_identity_number", columnNames = {"IdentityNumber"}),
        @UniqueConstraint(name = "uk_user_phone_number", columnNames = {"PhoneNumber"})
})
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

    @JoinColumn(name = "AccountID")
    Integer accountId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<Facility> facilities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<LocationManager> locationManagers = new HashSet<>();

//
//	public Integer getUserId() {
//		return userId;
//	}
//
//	public void setUserId(Integer userId) {
//		this.userId = userId;
//	}
//
//	public String getFullName() {
//		return fullName;
//	}
//
//	public void setFullName(String fullName) {
//		this.fullName = fullName;
//	}
//
//	public String getIdentityNumber() {
//		return identityNumber;
//	}
//
//	public void setIdentityNumber(String identityNumber) {
//		this.identityNumber = identityNumber;
//	}
//
//	public String getPhoneNumber() {
//		return phoneNumber;
//	}
//
//	public void setPhoneNumber(String phoneNumber) {
//		this.phoneNumber = phoneNumber;
//	}
//
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public Boolean getIsActive() {
//		return isActive;
//	}
//
//	public void setIsActive(Boolean isActive) {
//		this.isActive = isActive;
//	}
//
//	public String getProfilePicture() {
//		return profilePicture;
//	}
//
//	public void setProfilePicture(String profilePicture) {
//		this.profilePicture = profilePicture;
//	}
//
//	public Account getAccount() {
//		return account;
//	}
//
//	public void setAccount(Account account) {
//		this.account = account;
//	}
//
//	public Set<Facility> getFacilities() {
//		return facilities;
//	}
//
//	public void setFacilities(Set<Facility> facilities) {
//		this.facilities = facilities;
//	}
//
//	public Set<Invoice> getInvoices() {
//		return invoices;
//	}
//
//	public void setInvoices(Set<Invoice> invoices) {
//		this.invoices = invoices;
//	}
}
