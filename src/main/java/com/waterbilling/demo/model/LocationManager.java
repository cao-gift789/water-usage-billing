package com.waterbilling.demo.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "LocationManager")
public class LocationManager {

    
	@Id
    @ManyToOne
    @JoinColumn(name = "UserID", foreignKey = @ForeignKey(name = "fk_locationmanager_user"))
    User user;

    @ManyToOne
    @JoinColumn(name = "FacilityID", foreignKey = @ForeignKey(name = "fk_locationmanager_facility"))
    Facility facility;

    @Column(name = "GrantedDate", updatable = false)
    LocalDateTime grantedDate;


}
