package com.studentsviolation.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.studentsviolation.main.entity.OaViolations;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface OaViolationsRepository extends JpaRepository<OaViolations, Long> {
	OaViolations findByViolationName(String name);
}
