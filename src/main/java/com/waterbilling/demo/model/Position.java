package com.waterbilling.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PositionID")
    private Integer positionId;

    @Column(name = "NamePosition", length = 255)
    private String namePosition;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;

}
