package com.studentsviolation.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentsviolation.main.entity.CrimViolations;
import com.studentsviolation.main.entity.EducViolations;
import com.studentsviolation.main.entity.IsViolations;
import com.studentsviolation.main.entity.OaViolations;
import com.studentsviolation.main.entity.Students;
import com.studentsviolation.main.entity.ViolationRecords;
import com.studentsviolation.main.entity.Violations;
import com.studentsviolation.main.repository.CrimViolationsRepository;
import com.studentsviolation.main.repository.EducViolationsRepository;
import com.studentsviolation.main.repository.IsViolationsRepository;
import com.studentsviolation.main.repository.OaViolationsRepository;
import com.studentsviolation.main.repository.ViolationsCountRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartmentViolationsService {
	@Autowired
	private CrimViolationsRepository crimViolationsRepository;
	@Autowired
	private OaViolationsRepository oaViolationsRepository;
	@Autowired
	private IsViolationsRepository isViolationsRepository;
	@Autowired
	private EducViolationsRepository educViolationsRepository;
	@Transactional
	public List<CrimViolations> getCrimAllViolationsCount() {
		return crimViolationsRepository.findAll();
	}
	//CRIM
	@Transactional
	public CrimViolations getCrimViolationById(Long id) {
        return crimViolationsRepository.findById(id).orElse(null);
    }
	@Transactional
	public CrimViolations getCrimViolationByViolationName(String name) {
		return crimViolationsRepository.findByViolationName(name);
	}
	@Transactional
	public CrimViolations updateCrimViolationsCount(Long id, CrimViolations crimViolationDetails) {
		CrimViolations violation = crimViolationsRepository.findById(id).orElse(null);
        if (violation != null) {
        	violation.setCount(crimViolationDetails.getCount());
  
            return crimViolationsRepository.save(violation);
        }
        return null;
    }
	//IS
	@Transactional
	public List<IsViolations> getIsAllViolationsCount() {
		return isViolationsRepository.findAll();
	}
	@Transactional
	public IsViolations getIsViolationById(Long id) {
        return isViolationsRepository.findById(id).orElse(null);
    }
	@Transactional
	public IsViolations getIsViolationByViolationName(String name) {
		return isViolationsRepository.findByViolationName(name);
	}
	@Transactional
	public IsViolations updateIsViolationsCount(Long id, IsViolations isViolationDetails) {
		IsViolations violation = isViolationsRepository.findById(id).orElse(null);
        if (violation != null) {
        	violation.setCount(isViolationDetails.getCount());
  
            return isViolationsRepository.save(violation);
        }
        return null;
    }
	//AO
	@Transactional
	public List<OaViolations> getOaAllViolationsCount() {
		return oaViolationsRepository.findAll();
	}
	@Transactional
	public OaViolations getOaViolationById(Long id) {
        return oaViolationsRepository.findById(id).orElse(null);
    }
	@Transactional
	public OaViolations getOaViolationByViolationName(String name) {
		return oaViolationsRepository.findByViolationName(name);
	}
	@Transactional
	public OaViolations updateOaViolationsCount(Long id, OaViolations OaViolationDetails) {
		OaViolations violation = oaViolationsRepository.findById(id).orElse(null);
        if (violation != null) {
        	violation.setCount(OaViolationDetails.getCount());
  
            return oaViolationsRepository.save(violation);
        }
        return null;
    }
	//EDUC
	@Transactional
	public List<EducViolations> getEducAllViolationsCount() {
		return educViolationsRepository.findAll();
	}
	@Transactional
	public EducViolations getEducViolationById(Long id) {
        return educViolationsRepository.findById(id).orElse(null);
    }
	@Transactional
	public EducViolations getEducViolationByViolationName(String name) {
		return educViolationsRepository.findByViolationName(name);
	}
	@Transactional
	public EducViolations updateEducViolationsCount(Long id, EducViolations educViolationDetails) {
		EducViolations violation = educViolationsRepository.findById(id).orElse(null);
        if (violation != null) {
        	violation.setCount(educViolationDetails.getCount());
  
            return educViolationsRepository.save(violation);
        }
        return null;
    }
}
