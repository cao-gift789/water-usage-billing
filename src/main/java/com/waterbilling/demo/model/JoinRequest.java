package com.waterbilling.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "JoinRequest")
public class JoinRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RequestID")
    private Integer requestId;

    @ManyToOne
    @JoinColumn(name = "UserID", foreignKey = @ForeignKey(name = "fk_joinrequest_user"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "FacilityID", foreignKey = @ForeignKey(name = "fk_joinrequest_facility"))
    private Facility facility;

    @Column(name = "RequestDate", updatable = false)
    private LocalDateTime requestDate;

    @Column(name = "Note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "ProcessedDate")
    private LocalDateTime processedDate;

    // Constructors
    public JoinRequest() {
    }

    public JoinRequest(User user, Facility facility, String note) {
        this.user = user;
        this.facility = facility;
        this.note = note;
        this.requestDate = LocalDateTime.now();
    }

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

	public LocalDateTime getProcessedDate() {
		return processedDate;
	}

	public void setProcessedDate(LocalDateTime processedDate) {
		this.processedDate = processedDate;
	}

}
