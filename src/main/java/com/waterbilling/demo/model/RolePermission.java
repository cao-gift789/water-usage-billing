package com.waterbilling.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Role_Permission")
public class RolePermission {

    @Id
    @ManyToOne
    @JoinColumn(name = "RoleID", foreignKey = @ForeignKey(name = "fk_role_permission_role"))
    private Role rolePermission_role;

    @Id
    @ManyToOne
    @JoinColumn(name = "PermissionID", foreignKey = @ForeignKey(name = "fk_role_permission_permission"))
    private Permission permissionRole_permission;

    


	// Constructors
    public RolePermission() {
    }




	public Role getRolePermission_role() {
		return rolePermission_role;
	}




	public void setRolePermission_role(Role rolePermission_role) {
		this.rolePermission_role = rolePermission_role;
	}




	public Permission getPermissionRole_permission() {
		return permissionRole_permission;
	}




	public void setPermissionRole_permission(Permission permissionRole_permission) {
		this.permissionRole_permission = permissionRole_permission;
	}




	
	

    


}
