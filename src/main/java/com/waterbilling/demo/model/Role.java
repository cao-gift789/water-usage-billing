package com.waterbilling.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoleID")
    private Integer roleId;

    @Column(name = "RoleName", nullable = false, unique = true, length = 100)
    private String roleName;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "account_role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Account> role_account =new ArrayList<>();
    
    @OneToMany(mappedBy = "rolePermission_role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RolePermission> role_rolePermission=new ArrayList<>();
    
    public List<RolePermission> getRolePermissions() {
		return role_rolePermission;
	}

	public void setRolePermissions(List<RolePermission> rolePermissions) {
		this.role_rolePermission = rolePermissions;
	}

	// Constructors
    public Role() {
    }

    public Role(String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Account> getRole_account() {
		return role_account;
	}

	public void setRole_account(List<Account> role_account) {
		this.role_account = role_account;
	}

	public List<RolePermission> getRole_rolePermission() {
		return role_rolePermission;
	}

	public void setRole_rolePermission(List<RolePermission> role_rolePermission) {
		this.role_rolePermission = role_rolePermission;
	}

    
}
