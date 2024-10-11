package com.studentsviolation.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentsviolation.main.entity.IsViolations;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface IsViolationsRepository extends JpaRepository<IsViolations, Long> {
	IsViolations findByViolationName(String name);
}
