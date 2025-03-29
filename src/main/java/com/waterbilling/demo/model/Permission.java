package com.waterbilling.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PermissionID")
    private Integer permissionId;

    @Column(name = "PermissionName", nullable = false, length = 100)
    private String permissionName;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;

    // Constructors
    public Permission() {
    }
   
    @OneToMany(mappedBy = "permissionRole_permission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RolePermission> permission_permissionRole =new ArrayList<>();

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RolePermission> getPermission_permissionRole() {
		return permission_permissionRole;
	}

	public void setPermission_permissionRole(List<RolePermission> permission_permissionRole) {
		this.permission_permissionRole = permission_permissionRole;
	}

   

}
