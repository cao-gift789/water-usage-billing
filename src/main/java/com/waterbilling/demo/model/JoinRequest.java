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
    @JoinColumn(name = "UserID", foreignKey = @ForeignKey(name = "fk_joinrequest_user"))
    User user;

    @ManyToOne
    @JoinColumn(name = "FacilityID", foreignKey = @ForeignKey(name = "fk_joinrequest_facility"))
    Facility facility;

    @Column(name = "RequestDate", updatable = false)
    LocalDateTime requestDate;

    @Column(name = "Note", columnDefinition = "TEXT")
    String note;

}
