package com.studentsviolation.main.entity;

import java.util.List;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity

public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getCourseYear() {
		return courseYear;
	}
	public void setCourseYear(String courseYear) {
		this.courseYear = courseYear;
	}
	@OneToMany(mappedBy = "students")
    private List<ViolationRecords> violationRecords;
	@Column
    private String firstname;
    @Column
	private String lastname;
	@Column
	private String courseYear;
	@Column(name="violations_count")
	private int violationsCount;
	public List<ViolationRecords> getViolationRecords() {
		return violationRecords;
	}
	public void setViolationRecords(List<ViolationRecords> violationRecords) {
		this.violationRecords = violationRecords;
	}
	public int getViolationsCount() {
		return violationsCount;
	}
	public void setViolationsCount(int violationsCount) {
		this.violationsCount = violationsCount;
	}
	@Column
	private String programCode;
	@Column String middlename;
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	public String getProgramCode() {
		return programCode;
	}
	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}
	public long getYear() {
		return year;
	}
	public void setYear(long year) {
		this.year = year;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	@Column
	private long year;
	@Column
	private String section;
	@Column
	private String username;
	@Column
	private String password;
	public String getProgram_description() {
		return program_description;
	}
	public void setProgram_description(String program_description) {
		this.program_description = program_description;
	}
	@Column
	private String program_description;
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
	
}
