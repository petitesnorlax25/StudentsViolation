package com.studentsviolation.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentsviolation.main.entity.Violations;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface ViolationsCountRepository extends JpaRepository<Violations, Long> {
	Violations findByViolationName(String name);
}
