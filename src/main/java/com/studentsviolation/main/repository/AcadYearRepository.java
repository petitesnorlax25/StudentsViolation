package com.studentsviolation.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentsviolation.main.entity.AcademicYear;

import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface AcadYearRepository extends JpaRepository<AcademicYear, Long> {

	AcademicYear findById(long id);
	AcademicYear findByStatus(long status);
}

