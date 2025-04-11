package com.waterbilling.demo.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
public class UserUpdateRequest {

    String fullName;
    String identityNumber;
    String phoneNumber;
    String email;
    String profilePicture;

}
