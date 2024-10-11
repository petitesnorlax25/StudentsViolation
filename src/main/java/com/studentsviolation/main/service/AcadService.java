package com.studentsviolation.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentsviolation.main.entity.AcademicYear;
import com.studentsviolation.main.repository.AcadYearRepository;

import org.springframework.transaction.annotation.Transactional;


@Service
public class AcadService {
	@Autowired
	private AcadYearRepository acadYearRepo;
	@Transactional
	public AcademicYear updateAcad(Long id, AcademicYear acadDetails) {
		AcademicYear academicYear = acadYearRepo.findById(id).orElse(null);
        if (academicYear != null) {
        	academicYear.setStatus(acadDetails.getStatus());
        	
            return acadYearRepo.save(academicYear);
        }
        return null;
    }
	
	
}
