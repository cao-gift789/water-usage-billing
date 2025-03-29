package com.waterbilling.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NotificationID")
    private Integer notificationId;

    @Column(name = "CreatedDate", updatable = false)
    private LocalDateTime createdDate;

    

    @Column(name = "Title", nullable = false, length = 255)
    private String title;

    @Column(name = "Content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "NotificationTypeID", foreignKey = @ForeignKey(name = "fk_notificationType"))
    private NotificationType notificationType;
    
    @ManyToOne
    @JoinColumn(name = "SenderID", foreignKey = @ForeignKey(name = "fk_notification_employee"))
    private Employee notification_employee;
    
    @OneToMany(mappedBy = "NotificationFacility_Notification", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<NotificationFacility> Notification_NotificationFacility=new ArrayList<>();

    // Constructors
    public Notification() {
    }

    public Notification(Employee sender, String title, String content, NotificationType notificationType) {
        this.notification_employee = sender;
        this.title = title;
        this.content = content;
        this.notificationType = notificationType;
        this.createdDate = LocalDateTime.now();
    }

	public Integer getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public NotificationType getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}

	public Employee getNotification_employee() {
		return notification_employee;
	}

	public void setNotification_employee(Employee notification_employee) {
		this.notification_employee = notification_employee;
	}

	public List<NotificationFacility> getNotification_NotificationFacility() {
		return Notification_NotificationFacility;
	}

	public void setNotification_NotificationFacility(List<NotificationFacility> notification_NotificationFacility) {
		Notification_NotificationFacility = notification_NotificationFacility;
	}
	
	
	
	


	
}
