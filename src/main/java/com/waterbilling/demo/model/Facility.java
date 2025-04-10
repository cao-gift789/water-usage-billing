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
@Table(name = "Facility")
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FacilityID")
    Integer facilityId;

    @Column(name = "Address", length = 255)
    String address;

    @Column(name = "RegistrationDate", updatable = false)
    LocalDateTime registrationDate;

    @ManyToOne
    @JoinColumn(name = "OwnerId", foreignKey = @ForeignKey(name = "fk_facility_user"))
    User facility_user;

    @ManyToOne
    @JoinColumn(name = "FacilityTypeID", foreignKey = @ForeignKey(name = "fk_facility_customertype"))
    FacilityType facilityType;
    

    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<WaterMeter> waterMeters =new HashSet<>();
    
    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<NotificationFacility> notificationFacilities =new HashSet<>();

    
    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<Invoice> invoices =new HashSet<>();

    @Column(name = "IsActive")
    Boolean isActive = true;

}
