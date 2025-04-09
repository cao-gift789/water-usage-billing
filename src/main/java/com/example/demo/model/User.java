package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Integer userId;

    @Column(name = "FullName", nullable = false, length = 50)
    private String fullName;

    @Column(name = "IdentityNumber", nullable = false, unique = true, length = 12)
    private String identityNumber;

    @Column(name = "PhoneNumber", nullable = false, unique = true, length = 15)
    private String phoneNumber;

    @Column(name = "Address", length = 255)
    private String address;

    @Column(name = "Email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "DateOfBirth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "Gender", nullable = false)
    private Gender gender;

    @Column(name = "IsActive")
    private Boolean isActive = true;

    @Column(name = "ProfilePicture", length = 255)
    private String profilePicture;

    @OneToOne
    @JoinColumn(name = "AccountID", foreignKey = @ForeignKey(name = "fk_user_account"))
    private Account account;

    @OneToMany(mappedBy = "facility_user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)	
    private List<Facility> user_facility;
    
    @OneToMany(mappedBy = "invoice_user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Invoice> user_invoice;
    
    @OneToMany(mappedBy = "locationManager_user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LocationManager> user_locationManager;
    
    @OneToMany(mappedBy = "joinRequest_user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<JoinRequest> user_joinRequest;

    // Enum for Gender
    public enum Gender {
        male, female
    }

    // Constructors
    public User() {
    }

    public User(String fullName, String identityNumber, String phoneNumber, String email, LocalDate dateOfBirth, Gender gender, Account account) {
        this.fullName = fullName;
        this.identityNumber = identityNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.account = account;
        this.isActive = true;
    }

    // Getters and Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

	public List<Facility> getUser_facility() {
		return user_facility;
	}

	public void setUser_facility(List<Facility> user_facility) {
		this.user_facility = user_facility;
	}

	public List<Invoice> getUser_invoice() {
		return user_invoice;
	}

	public void setUser_invoice(List<Invoice> user_invoice) {
		this.user_invoice = user_invoice;
	}

	public List<LocationManager> getUser_locationManager() {
		return user_locationManager;
	}

	public void setUser_locationManager(List<LocationManager> user_locationManager) {
		this.user_locationManager = user_locationManager;
	}

	public List<JoinRequest> getUser_joinRequest() {
		return user_joinRequest;
	}

	public void setUser_joinRequest(List<JoinRequest> user_joinRequest) {
		this.user_joinRequest = user_joinRequest;
	}

    	
    
}
