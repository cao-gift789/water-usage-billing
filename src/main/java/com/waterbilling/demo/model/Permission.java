package com.waterbilling.demo.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PermissionID")
    Integer permissionId;

    @Column(name = "PermissionName", nullable = false, length = 100)
    String permissionName;

    @Column(name = "Description", columnDefinition = "TEXT")
    String description;


	@ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
	private Set<Role> roles = new HashSet<>();

}