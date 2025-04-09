package com.example.demo.model;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PositionID")
    private Integer positionId;
    
    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> position_employee=new ArrayList<>();

    @Column(name = "NamePosition", length = 255)
    private String namePosition;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public List<Employee> getPosition_employee() {
		return position_employee;
	}

	public void setPosition_employee(List<Employee> position_employee) {
		this.position_employee = position_employee;
	}

	public String getNamePosition() {
		return namePosition;
	}

	public void setNamePosition(String namePosition) {
		this.namePosition = namePosition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
    

    
}
