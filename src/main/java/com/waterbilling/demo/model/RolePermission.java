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
@Table(name = "Role_Permission")
public class RolePermission {

    @Id
    @ManyToOne
    @JoinColumn(name = "RoleID", foreignKey = @ForeignKey(name = "fk_role_permission_role"))
    Role role;

    @Id
    @ManyToOne
    @JoinColumn(name = "PermissionID", foreignKey = @ForeignKey(name = "fk_role_permission_permission"))
    Permission permission;


}
