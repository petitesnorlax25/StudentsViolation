package com.studentsviolation.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentsviolation.main.entity.Students;
import com.studentsviolation.main.entity.ViolationRecords;
import com.studentsviolation.main.entity.Violations;
import com.studentsviolation.main.repository.ViolationsCountRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ViolationsCountService {
	@Autowired
	private ViolationsCountRepository violationCountRepository;
	@Transactional
	public List<Violations> getAllViolationsCount() {
		return violationCountRepository.findAll();
	}
	@Transactional
	public Violations getViolationById(Long id) {
        return violationCountRepository.findById(id).orElse(null);
    }
	@Transactional
	public Violations getViolationByViolationName(String name) {
		return violationCountRepository.findByViolationName(name);
	}
	@Transactional
	public Violations updateViolationsCount(Long id, Violations violationDetails) {
		Violations violation = violationCountRepository.findById(id).orElse(null);
        if (violation != null) {
        	violation.setCount(violationDetails.getCount());
  
            return violationCountRepository.save(violation);
        }
        return null;
    }
}
