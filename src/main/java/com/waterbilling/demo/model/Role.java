package com.waterbilling.demo.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
@Table(name = "Role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoleID")
    Integer roleId;

    @Column(name = "RoleName", nullable = false, unique = true, length = 100)
    String roleName;

    @Column(name = "Description", columnDefinition = "TEXT")
    String description;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
	@JoinTable(
			name = "Role_Permission",
			joinColumns = @JoinColumn(name = "RoleID"),
			inverseJoinColumns = @JoinColumn(name = "PermissionID")
	)
    Set<Permission> permissions = new HashSet<>();


}
