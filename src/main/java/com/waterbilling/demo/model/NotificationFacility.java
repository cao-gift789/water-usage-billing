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

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public NotificationStatus getStatus() {
		return status;
	}

	public void setStatus(NotificationStatus status) {
		this.status = status;
	}

    
}
