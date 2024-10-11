package com.studentsviolation.main;

public class ViolationRecordDTO {
    private Long id;
    private String userCode;
    private String violationType;
    private String violationDate;
    private String resolutionDate;
    private String disciplinaryAction;
    private int deptHeadApproval;
    private int disciplinaryOfficerApproval;
    private int guidanceOfficerApproval;

    // Getters and Setters
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

    public String getResolutionDate() {
        return resolutionDate;
    }

    public void setResolutionDate(String resolutionDate) {
        this.resolutionDate = resolutionDate;
    }

    public String getDisciplinaryAction() {
        return disciplinaryAction;
    }

    public void setDisciplinaryAction(String disciplinaryAction) {
        this.disciplinaryAction = disciplinaryAction;
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
