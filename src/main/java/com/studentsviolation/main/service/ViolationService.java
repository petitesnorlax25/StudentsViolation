package com.studentsviolation.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentsviolation.main.entity.Students;
import com.studentsviolation.main.entity.ViolationRecords;
import com.studentsviolation.main.repository.ViolationRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ViolationService {
	@Autowired
	private ViolationRepository violationRepository;
	@Transactional
	public List<ViolationRecords> getAllViolations() {
		return violationRepository.findAll();
	}
	@Transactional
	public ViolationRecords getViolationById(Long id) {
        return violationRepository.findById(id).orElse(null);
    }
	@Transactional
	public List<ViolationRecords> getViolationByCourse(String course) {
        return violationRepository.findByCourse(course);
    }
	@Transactional
	public List<ViolationRecords> getViolationByStatus(int status) {
        return violationRepository.findByStatus(status);
    }
	@Transactional
	public List<ViolationRecords> getViolationByStudentsId(long id) {
        return violationRepository.findByStudentsId(id);
    }
	@Transactional
	public List<ViolationRecords>  getViolationByYear(long year) {
        return violationRepository.findByYear(year);
    }
	@Transactional
	public List<ViolationRecords> getViolationByCourseYearAndSection(String course, long year, String section) {
        return violationRepository.findByCourseAndYearAndSection(course, year, section);
    }
	@Transactional
	public ViolationRecords createViolationRecord(ViolationRecords violationRecord) {
        return violationRepository.save(violationRecord);
    }
	@Transactional
	public ViolationRecords createViolation(Students student, ViolationRecords violation) {
		violation.setStudents(student);
    	return violationRepository.save(violation);
    }
	@Transactional
	public List<ViolationRecords> getViolationByUsercode(String usercode) {
		return violationRepository.findByUsercode(usercode);
	}
	@Transactional
	public List<ViolationRecords> getViolationByUsercodeAndStatus(String usercode, long status) {
		return violationRepository.findByUsercodeAndStatus(usercode,  status);
	}
	@Transactional
	public List<ViolationRecords> getViolationByProgramCodeAndStatus(String programCode, long status) {
		return violationRepository.findByProgramCodeAndStatus(programCode,  status);
	}
	@Transactional
	public List<ViolationRecords> getViolationByProgramCodeAndStatusAndDeptHeadApproval(String programCode, long status, long deptHeadApproval) {
		return violationRepository.findViolationRecordsByProgramCodeAndStatusAndDeptHeadApproval(programCode,  status, deptHeadApproval);
	}
	@Transactional
	public ViolationRecords updateViolations(Long id, ViolationRecords violationDetails) {
		ViolationRecords violation = violationRepository.findById(id).orElse(null);
        if (violation != null) {
        	violation.setDeptHeadApproval(violationDetails.getDeptHeadApproval());
  
            return violationRepository.save(violation);
        }
        return null;
    }
	@Transactional
	public List<ViolationRecords> getViolationRecordsByGuidanceOfficerApproval (long guidanceOfficerApproval) {
		return violationRepository.findViolationRecordsByGuidanceOfficerApproval(guidanceOfficerApproval);
	}
	@Transactional
	public List<ViolationRecords> getViolationRecordsByDisciplinaryOfficerApproval (long disciplinaryOfficerApproval) {
		return violationRepository.findViolationRecordsByDisciplinaryOfficerApproval(disciplinaryOfficerApproval);
	}
	@Transactional
	public List<ViolationRecords> getViolationRecordsByProgramCodeAndDeptHeadApproval (String programCode, long deptHeadApprovalApproval) {
		return violationRepository.findViolationRecordsByProgramCodeAndDeptHeadApproval(programCode, deptHeadApprovalApproval);
	}

}
