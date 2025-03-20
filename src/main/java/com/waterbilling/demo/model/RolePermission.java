package com.waterbilling.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Role_Permission")
public class RolePermission {

    @Id
    @ManyToOne
    @JoinColumn(name = "RoleID", foreignKey = @ForeignKey(name = "fk_role_permission_role"))
    private Role role;

    @Id
    @ManyToOne
    @JoinColumn(name = "PermissionID", foreignKey = @ForeignKey(name = "fk_role_permission_permission"))
    private Permission permission;

    // Constructors
    public RolePermission() {
    }

    public RolePermission(Role role, Permission permission) {
        this.role = role;
        this.permission = permission;
    }

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

}
