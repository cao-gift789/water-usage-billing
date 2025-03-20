package com.waterbilling.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmployeeID")
    private Integer employeeId;

    @Column(name = "FullName", nullable = false, length = 50)
    private String fullName;

    @Column(name = "PhoneNumber", nullable = false, unique = true, length = 15)
    private String phoneNumber;

    @Column(name = "Address", nullable = false, length = 255)
    private String address;

    @Column(name = "Email", nullable = false, unique = true, length = 255)
    private String email;

    @OneToOne
    @JoinColumn(name = "AccountID", foreignKey = @ForeignKey(name = "fk_employee_account"))
    private Account account;

    @Column(name = "StartDate", updatable = false)
    private LocalDateTime startDate;

    @ManyToOne
    @JoinColumn(name = "PositionID", foreignKey = @ForeignKey(name = "fk_employee_position"))
    private Position position;

    @Column(name = "Image", length = 255)
    private String image;

    // Constructors
    public Employee() {
    }

    public Employee(String fullName, String phoneNumber, String address, String email, Account account, Position position, String image) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.account = account;
        this.position = position;
        this.startDate = LocalDateTime.now();
        this.image = image;
    }

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
