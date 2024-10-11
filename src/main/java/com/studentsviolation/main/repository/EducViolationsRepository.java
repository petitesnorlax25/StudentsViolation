package com.studentsviolation.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.studentsviolation.main.entity.EducViolations;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface EducViolationsRepository extends JpaRepository<EducViolations, Long> {
	EducViolations findByViolationName(String name);
}
