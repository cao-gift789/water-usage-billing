package com.waterbilling.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Notification_Facility")
public class NotificationFacility {

    @Id
    @ManyToOne
    @JoinColumn(name = "NotificationID", foreignKey = @ForeignKey(name = "fk_notification_facility_notification"))
    private Notification notification;

    @Id
    @ManyToOne
    @JoinColumn(name = "FacilityID", foreignKey = @ForeignKey(name = "fk_notification_facility_facility"))
    private Facility facility;

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
        this.notification = notification;
        this.facility = facility;
        this.status = status;
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
