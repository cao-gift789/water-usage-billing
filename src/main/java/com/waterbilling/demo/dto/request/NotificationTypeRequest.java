package com.waterbilling.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationTypeRequest {

    @NotBlank(message = "Tên quyền không được bỏ trống!")
    String typeName;
    String description;
}
