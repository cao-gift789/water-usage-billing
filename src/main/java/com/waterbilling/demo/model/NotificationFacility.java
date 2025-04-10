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
@Entity
@Table(name = "Notification_Facility")
public class NotificationFacility {

    @Id
    @ManyToOne
    @JoinColumn(name = "NotificationID", foreignKey = @ForeignKey(name = "fk_notification_facility_notification"))
    Notification notification;

    @Id
    @ManyToOne
    @JoinColumn(name = "FacilityID", foreignKey = @ForeignKey(name = "fk_notification_facility_facility"))
    Facility facility;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false)
    NotificationStatus status;

    public enum NotificationStatus {
        Read, Unread
    }

}
