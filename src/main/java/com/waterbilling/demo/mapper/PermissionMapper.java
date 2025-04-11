package com.waterbilling.demo.mapper;

import com.waterbilling.demo.dto.request.PermissionRequest;
import com.waterbilling.demo.dto.response.PermissionResponse;
import com.waterbilling.demo.model.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}

