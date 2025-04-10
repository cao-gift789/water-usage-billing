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
@FieldDefaults(level=AccessLevel.PRIVATE)
@Entity
@Table(name = "WaterMeter")
public class WaterMeter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WaterMeterID")
    Integer waterMeterId;

    @Column(name = "SerialNumber", nullable = false, length = 50)
    String serialNumber;

    @ManyToOne
    @JoinColumn(name = "FacilityID", foreignKey = @ForeignKey(name = "fk_watermeter_facility"))
    Facility facility;

    @Column(name = "InstallationDate")
    LocalDateTime installationDate = LocalDateTime.now();

    @Column(name = "IsActive")
    Boolean isActive = true;
    
    @OneToMany(mappedBy = "waterMeter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	Set<WaterMeterReading> waterMeterReadings =new HashSet<>();


}
