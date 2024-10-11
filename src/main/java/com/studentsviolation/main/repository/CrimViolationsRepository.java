package com.studentsviolation.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentsviolation.main.entity.CrimViolations;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface CrimViolationsRepository extends JpaRepository<CrimViolations, Long> {
	CrimViolations findByViolationName(String name);
}
