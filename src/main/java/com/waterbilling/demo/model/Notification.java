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

//	public Integer getNotificationId() {
//		return notificationId;
//	}
//
//	public void setNotificationId(Integer notificationId) {
//		this.notificationId = notificationId;
//	}
//
//	public LocalDateTime getCreatedDate() {
//		return createdDate;
//	}
//
//	public void setCreatedDate(LocalDateTime createdDate) {
//		this.createdDate = createdDate;
//	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getContent() {
//		return content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
//
//	public NotificationType getNotificationType() {
//		return notificationType;
//	}
//
//	public void setNotificationType(NotificationType notificationType) {
//		this.notificationType = notificationType;
//	}
//
//	public Employee getEmployee() {
//		return employee;
//	}
//
//	public void setEmployee(Employee employee) {
//		this.employee = employee;
//	}
//
//	public Set<NotificationFacility> getNotificationFacilities() {
//		return notificationFacilities;
//	}
//
//	public void setNotificationFacilities(Set<NotificationFacility> notificationFacilities) {
//		this.notificationFacilities = notificationFacilities;
//	}

    
    
    
}
