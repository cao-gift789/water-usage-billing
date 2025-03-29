package com.waterbilling.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Notification_Facility")
public class NotificationFacility {

    @Id
    @ManyToOne
    @JoinColumn(name = "NotificationID", foreignKey = @ForeignKey(name = "fk_notification_facility_notification"))
    private Notification NotificationFacility_Notification;

    @Id
    @ManyToOne
    @JoinColumn(name = "FacilityID", foreignKey = @ForeignKey(name = "fk_notification_facility_facility"))
    private Facility notificationFacility_facility;
    

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false)
    private NotificationStatus status;

    public enum NotificationStatus {
        Read, Unread
    }

    // Constructors
    public NotificationFacility() {
    }

    public NotificationFacility(Notification notification, Facility facility, NotificationStatus status) {
        this.NotificationFacility_Notification = notification;
        this.notificationFacility_facility = facility;
        this.status = status;
    }

	public Notification getNotificationFacility_Notification() {
		return NotificationFacility_Notification;
	}

	public void setNotificationFacility_Notification(Notification notificationFacility_Notification) {
		NotificationFacility_Notification = notificationFacility_Notification;
	}

	

	public Facility getNotificationFacility_facility() {
		return notificationFacility_facility;
	}

	public void setNotificationFacility_facility(Facility notificationFacility_facility) {
		this.notificationFacility_facility = notificationFacility_facility;
	}

	public NotificationStatus getStatus() {
		return status;
	}

	public void setStatus(NotificationStatus status) {
		this.status = status;
	}

	

}
