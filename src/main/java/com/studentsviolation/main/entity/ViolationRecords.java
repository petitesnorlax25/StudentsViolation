package com.studentsviolation.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ViolationRecords {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(name="user_code")
	private String userCode;
	@Column
	private long status;
	@Column
	private String sanction;
	@Column(name="violation_type")
	private String violationType;
	@Column(name="violation_date")
	private String violationDate;
	@Column(name="resolution_date")
	private String resolutionDate;
	@Column(name="disciplinary_action")
	private String disciplinaryAction;
	@Column
	private String academicYear;
	@Column
	private String semester;
	@Column(name="dept_head_approval")
	private int deptHeadApproval;
	@Column(name="disciplinary_officer_approval")
	private int disciplinaryOfficerApproval;
	@Column(name="guidance_officer_approval")
	private int guidanceOfficerApproval;
	@Column
	private long strike;
	public String getResolutionDate() {
		return resolutionDate;
	}

	public void setResolutionDate(String resolutionDate) {
		this.resolutionDate = resolutionDate;
	}
	@ManyToOne(fetch = FetchType.EAGER) 
    private Students students;
	public Students getStudents() {
	    return students;
	}

	public void setStudents(Students students) {
	    this.students = students;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getViolationType() {
		return violationType;
	}
	public void setViolationType(String violationType) {
		this.violationType = violationType;
	}
	public String getViolationDate() {
		return violationDate;
	}
	public void setViolationDate(String violationDate) {
		this.violationDate = violationDate;
	}
	public String getDisciplinaryAction() {
		return disciplinaryAction;
	}
	public void setDisciplinaryAction(String disciplinaryAction) {
		this.disciplinaryAction = disciplinaryAction;
	}
	public long getStrike() {
		return strike;
	}
	public void setStrike(long strike) {
		this.strike = strike;
	}
	public long getStatus() {
		return status;
	}
	public void setStatus(long status) {
		this.status = status;
	}

	public String getSanction() {
		return sanction;
	}

	public void setSanction(String sanction) {
		this.sanction = sanction;
	}
	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public int getDeptHeadApproval() {
		return deptHeadApproval;
	}

	public void setDeptHeadApproval(int deptHeadApproval) {
		this.deptHeadApproval = deptHeadApproval;
	}

	public int getDisciplinaryOfficerApproval() {
		return disciplinaryOfficerApproval;
	}

	public void setDisciplinaryOfficerApproval(int disciplinaryOfficerApproval) {
		this.disciplinaryOfficerApproval = disciplinaryOfficerApproval;
	}

	public int getGuidanceOfficerApproval() {
		return guidanceOfficerApproval;
	}

	public void setGuidanceOfficerApproval(int guidanceOfficerApproval) {
		this.guidanceOfficerApproval = guidanceOfficerApproval;
	}
	
}
