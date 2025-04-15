package com.waterbilling.demo.mapper;

import com.waterbilling.demo.dto.request.NotificationTypeRequest;
import com.waterbilling.demo.dto.response.NotificationTypeResponse;
import com.waterbilling.demo.model.NotificationType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationTypeMapper {

    NotificationType toNotificationType(NotificationTypeRequest request);

    NotificationTypeResponse toNotificationTypeResponse(NotificationType notificationType);
}
