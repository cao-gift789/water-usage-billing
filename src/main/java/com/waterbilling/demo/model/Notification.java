package com.waterbilling.demo.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NotificationID")
    Integer notificationId;

    @Column(name = "CreatedDate", updatable = false)
   	LocalDateTime createdDate;

    @Column(name = "Title", nullable = false, length = 255)
    String title;

    @Column(name = "Content", nullable = false, columnDefinition = "TEXT")
    String content;

    @ManyToOne
    @JoinColumn(name = "NotificationTypeID", foreignKey = @ForeignKey(name = "fk_notificationType"))
    NotificationType notificationType;
    
    @ManyToOne
    @JoinColumn(name = "SenderID", foreignKey = @ForeignKey(name = "fk_notification_employee"))
    Employee employee;
    
    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	Set<NotificationFacility> notificationFacilities = new HashSet<>();

}
