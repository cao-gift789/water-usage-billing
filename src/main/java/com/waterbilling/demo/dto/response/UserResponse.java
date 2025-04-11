package com.waterbilling.demo.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;



@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
public class UserResponse {

    String fullName;

    String identityNumber;

    String phoneNumber;

    String email;

    String profilePicture;

}