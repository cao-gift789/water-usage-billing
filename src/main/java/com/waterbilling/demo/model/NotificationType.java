package com.waterbilling.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "NotificationType")
public class NotificationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NotificationTypeID")
    private Integer notificationTypeId;

    @Column(name = "TypeName", length = 100)
    private String typeName;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;
    
    @OneToMany(mappedBy = "notificationType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notification> notifications=new ArrayList<>();

    // Constructors
    public NotificationType() {
    }

    public NotificationType(String typeName, String description) {
        this.typeName = typeName;
        this.description = description;
    }

	public Integer getNotificationTypeId() {
		return notificationTypeId;
	}

	public void setNotificationTypeId(Integer notificationTypeId) {
		this.notificationTypeId = notificationTypeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	
	

}
