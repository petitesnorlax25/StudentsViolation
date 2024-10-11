package com.studentsviolation.main.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import com.studentsviolation.main.entity.Students;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface StudentsRepository extends JpaRepository<Students, Long> {

	Students findByFirstname(String firstname);
	Students findByLastname(String lastname);
	Students findByUsernameAndPassword(String username, String password);
	Students findByFirstnameAndMiddlenameAndLastname(String firstname, String middlename, String lastname);
	Students findByUsername(String username);
	List<Students> findByCourseYear(String courseYear);
	List<Students> findByProgramCode(String programCode);
	Students findById(long id);
}

