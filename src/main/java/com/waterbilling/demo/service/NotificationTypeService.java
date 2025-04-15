package com.waterbilling.demo.service;

import com.waterbilling.demo.dto.request.NotificationTypeRequest;
import com.waterbilling.demo.dto.response.NotificationTypeResponse;
import com.waterbilling.demo.mapper.NotificationTypeMapper;
import com.waterbilling.demo.model.NotificationType;
import com.waterbilling.demo.repository.NotificationRepository;
import com.waterbilling.demo.repository.NotificationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationTypeService {

    @Autowired
    private NotificationTypeRepository notificationTypeRepository;

    @Autowired
    private NotificationTypeMapper notificationTypeMapper;

    public List<NotificationTypeResponse> getAll() {
        return notificationTypeRepository.findAll()
                .stream()
                .map(e -> notificationTypeMapper.toNotificationTypeResponse(e))
                .toList();

    }

    public NotificationTypeResponse getById(Integer id){
        NotificationType notificationType = notificationTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Kiểu thông báo này không tồn tại"));
        return notificationTypeMapper.toNotificationTypeResponse(notificationType);
    }

    public void create(NotificationTypeRequest request) {
        Optional<NotificationType> notificationType = notificationTypeRepository.findByTypeName(request.getTypeName());
        if(notificationType.isPresent())
            throw new IllegalArgumentException("TypeName đã tồn tại");
        notificationTypeRepository.save(notificationType.get());
    }

    public void update(Integer id ,NotificationTypeRequest request){
        Optional<NotificationType> notificationTypeOptional = notificationTypeRepository.findByTypeName(request.getTypeName());
        if(notificationTypeOptional.isPresent())
            throw new IllegalArgumentException("TypeName đã tồn tại");
        NotificationType notificationType = notificationTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Kiểu thông báo này không tồn tại"));
        notificationTypeRepository.save(notificationTypeMapper.toNotificationType(request));
    }

    public void delete(Integer id) {
        NotificationType notificationType = notificationTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Kiểu thông báo này không tồn tại"));
        if(!notificationType.getNotifications().isEmpty())
            throw new IllegalArgumentException("Không xoas được vì đã được tham chiếu");
        notificationTypeRepository.delete(notificationType);
    }
}
