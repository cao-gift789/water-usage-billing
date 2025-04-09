package com.waterbilling.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "support")
public class Support {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TransactionID")
	private Integer id_support;
    
    @Column(name = "name")
	private String name;
    
    @Column(name = "phone_number")
	private String phone_number;
    
    @Column(name = "question_content")
	private String question_content;
    
    
    
	public Integer getId_support() {
		return id_support;
	}
	public void setId_support(Integer id_support) {
		this.id_support = id_support;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getQuestion_content() {
		return question_content;
	}
	public void setQuestion_content(String question_content) {
		this.question_content = question_content;
	}
	
	
}
