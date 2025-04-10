package com.waterbilling.demo.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmployeeID")
    Integer employeeId;

    @Column(name = "FullName", nullable = false, length = 50)
    String fullName;

    @Column(name = "PhoneNumber", nullable = false, unique = true, length = 15)
    String phoneNumber;

    @Column(name = "Address", nullable = false, length = 255)
    String address;

    @Column(name = "Email", nullable = false, unique = true, length = 255)
    String email;

    @ManyToOne
    @JoinColumn(name = "AccountID", foreignKey = @ForeignKey(name = "fk_employee_account"))
    Account account;

    @Column(name = "StartDate", updatable = false)
    LocalDateTime startDate;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	Set<News> news =new HashSet<>();
    
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	Set<Notification> notifications =new HashSet<>();
    
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	Set<WaterMeterReading> waterMeterReadings = new HashSet<>();
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	Set<Invoice> invoices =new HashSet<>();

    @Column(name = "Image", length = 255)
    String image;

    @Column(name = "IsActive")
    Boolean isActive = true;

}
