package com.studentsviolation.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OaViolations {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getViolationName() {
		return violationName;
	}
	public void setViolationName(String violationName) {
		this.violationName = violationName;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	@Column(name="violation_name")
	 private String violationName;
	 @Column
	 private Long count;
}