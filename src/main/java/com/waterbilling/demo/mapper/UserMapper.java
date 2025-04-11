package com.waterbilling.demo.mapper;

import com.waterbilling.demo.dto.request.UserUpdateRequest;
import com.waterbilling.demo.dto.response.UserResponse;
import com.waterbilling.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}