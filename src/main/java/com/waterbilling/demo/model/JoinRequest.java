package com.waterbilling.demo.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;




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
    @JoinColumn(name = "UserID", foreignKey = @ForeignKey(name = "fk_join request_user"))
    User user;

    @ManyToOne
    @JoinColumn(name = "FacilityID", foreignKey = @ForeignKey(name = "fk_join request_facility"))
    Facility facility;

    @Column(name = "RequestDate", updatable = false)
    LocalDateTime requestDate;


//    public void setRequestId(Integer requestId) {
//		this.requestId = requestId;
//	}
//
//    public void setUser(User user) {
//		this.user = user;
//	}
//
//    public void setProcessedDate(String processedDate) {
//		this.processedDate = processedDate;
//	}
//
//    public void setStatus(JoinStatus status) {
//		this.status = status;
//	}
//
//    public void setFacility(Facility facility) {
//		this.facility = facility;
//	}
//
//    public void setRequestDate(LocalDateTime requestDate) {
//		this.requestDate = requestDate;
//	}
//
//    public void setNote(String note) {
//		this.note = note;
//	}

    
    
}
