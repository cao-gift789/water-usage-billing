package com.waterbilling.demo.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "serviceregistration ")
public class ServiceRegistration {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	Integer id;

	@Column(name="name")
	String name;

	@Column(name="phonenumber")
	String phoneNumber;

	@Column(name="email")
	String email;

	@ManyToOne
	@JoinColumn(name = "facilityTypeID")
	FacilityType facilityType;

}
