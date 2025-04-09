package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AccountID")
    private Integer accountId;

    @Column(name = "Username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "Password", nullable = false, length = 255)
    private String password;

    @Column(name = "RegistrationDate", updatable = false)
    private LocalDateTime registrationDate;

    @ManyToOne
    @JoinColumn(name = "RoleID", foreignKey = @ForeignKey(name = "fk_account_role"))
    private Role account_role;
    
    @OneToMany(mappedBy = "employee_account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> account_employees=new ArrayList<>();

    // Constructors
    public Account() {
    }

    public Account(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.account_role = role;
        this.registrationDate = LocalDateTime.now();
    }

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Role getAccount_role() {
		return account_role;
	}

	public void setAccount_role(Role account_role) {
		this.account_role = account_role;
	}

	public List<Employee> getAccount_employees() {
		return account_employees;
	}

	public void setAccount_employees(List<Employee> account_employees) {
		this.account_employees = account_employees;
	}    

   
}
