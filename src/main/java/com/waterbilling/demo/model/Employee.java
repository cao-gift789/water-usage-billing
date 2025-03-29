package com.waterbilling.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "AccountID", foreignKey = @ForeignKey(name = "fk_employee_account"))
    private Account employee_account;

    @Column(name = "StartDate", updatable = false)
    private LocalDateTime startDate;

    @ManyToOne
    @JoinColumn(name = "PositionID", foreignKey = @ForeignKey(name = "fk_employee_position"))
    private Position position;
    
    @OneToMany(mappedBy = "news_employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<News> employee_news=new ArrayList<>();
    
    
    @OneToMany(mappedBy = "notification_employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notification> employee_notification=new ArrayList<>();
    
    
    @OneToMany(mappedBy = "WaterMeterReading_employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WaterMeterReading>employee_WaterMeterReading=new ArrayList<>();
    
    @OneToMany(mappedBy = "invoice_employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Invoice>employee_invoice=new ArrayList<>();

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
        this.employee_account = account;
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

	
	public List<WaterMeterReading> getEmployee_WaterMeterReading() {
		return employee_WaterMeterReading;
	}

	public void setEmployee_WaterMeterReading(List<WaterMeterReading> employee_WaterMeterReading) {
		this.employee_WaterMeterReading = employee_WaterMeterReading;
	}

	public List<Invoice> getEmployee_invoice() {
		return employee_invoice;
	}

	public void setEmployee_invoice(List<Invoice> employee_invoice) {
		this.employee_invoice = employee_invoice;
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

	public Account getEmployee_account() {
		return employee_account;
	}

	public void setEmployee_account(Account employee_account) {
		this.employee_account = employee_account;
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

	
	public List<News> getEmployee_news() {
		return employee_news;
	}

	public void setEmployee_news(List<News> employee_news) {
		this.employee_news = employee_news;
	}

	public List<Notification> getEmployee_notification() {
		return employee_notification;
	}

	public void setEmployee_notification(List<Notification> employee_notification) {
		this.employee_notification = employee_notification;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	

}
