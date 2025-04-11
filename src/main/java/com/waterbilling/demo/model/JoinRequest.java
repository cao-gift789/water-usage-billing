package com.waterbilling.demo.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import com.waterbilling.demo.enums.JoinStatus;
import com.waterbilling.demo.model.Invoice.InvoiceStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "JoinRequest")
public class JoinRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RequestID")
    Integer requestId;

    @ManyToOne
    @JoinColumn(name = "UserID", foreignKey = @ForeignKey(name = "fk_joinrequest_user"))
    User user;

    @ManyToOne
    @JoinColumn(name = "FacilityID", foreignKey = @ForeignKey(name = "fk_joinrequest_facility"))
    Facility facility;

    @Column(name = "RequestDate", updatable = false)
    LocalDateTime requestDate;

    @Column(name = "Note", columnDefinition = "TEXT")
    String note;

    @Column(name = "ProcessedDate", columnDefinition = "TEXT")
    String processedDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false)
    JoinStatus status;
    
    
    

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getProcessedDate() {
		return processedDate;
	}

	public void setProcessedDate(String processedDate) {
		this.processedDate = processedDate;
	}

	public JoinStatus getStatus() {
		return status;
	}

	public void setStatus(JoinStatus status) {
		this.status = status;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public LocalDateTime getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDateTime requestDate) {
		this.requestDate = requestDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

    
    
}
