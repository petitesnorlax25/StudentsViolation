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

public class AcademicYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column 
    private String semester;
    @Column 
    private String yearFrom;
    @Column 
    private String yearTo;
    @Column 
    private int status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getYearFrom() {
		return yearFrom;
	}
	public void setYearFrom(String yearFrom) {
		this.yearFrom = yearFrom;
	}
	public String getYearTo() {
		return yearTo;
	}
	public void setYearTo(String yearTo) {
		this.yearTo = yearTo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}