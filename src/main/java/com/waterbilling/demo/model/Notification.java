package com.waterbilling.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NotificationID")
    private Integer notificationId;

    @Column(name = "CreatedDate", updatable = false)
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "SenderID", foreignKey = @ForeignKey(name = "fk_notification_employee"))
    private Employee sender;

    @Column(name = "Title", nullable = false, length = 255)
    private String title;

    @Column(name = "Content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "NotificationTypeID", foreignKey = @ForeignKey(name = "fk_notification_type"))
    private NotificationType notificationType;

    // Constructors
    public Notification() {
    }

    public Notification(Employee sender, String title, String content, NotificationType notificationType) {
        this.sender = sender;
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

	public Employee getSender() {
		return sender;
	}

	public void setSender(Employee sender) {
		this.sender = sender;
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

}
