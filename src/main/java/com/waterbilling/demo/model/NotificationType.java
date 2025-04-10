package com.waterbilling.demo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "NotificationType")
public class NotificationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NotificationTypeID")
    Integer notificationTypeId;

    @Column(name = "TypeName", length = 100)
    String typeName;

    @Column(name = "Description", columnDefinition = "TEXT")
    String description;
    
    @OneToMany(mappedBy = "notificationType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	Set<Notification> notifications = new HashSet<>();


}
