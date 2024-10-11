package com.studentsviolation.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studentsviolation.main.entity.Students;
import com.studentsviolation.main.repository.StudentsRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentsService {
	@Autowired
	private StudentsRepository studentsRepository;
	@Transactional
	public List<Students> getAllStudents() {
		return studentsRepository.findAll();
	}
	@Transactional
	public Students getStudentsById(Long id) {
        return studentsRepository.findById(id).orElse(null);
    }
	@Transactional
	public Students getStudentsByFirstname(String firstname) {
        return studentsRepository.findByFirstname(firstname);
    }
	@Transactional
	public Students getStudentByLastname(String lastname) {
        return studentsRepository.findByLastname(lastname);
    }
	@Transactional
	public Students getStudentByFirstnameAndMiddlenameAndLastname(String firstname, String middlename, String lastname ) {
        return studentsRepository.findByFirstnameAndMiddlenameAndLastname(firstname, middlename, lastname);
    }
	@Transactional
	public Students getStudentByUsername(String username) {
        return studentsRepository.findByUsername(username);
    }
	@Transactional
	public Students getStudentByUsernameAndPassword(String username, String password) {
        return studentsRepository.findByUsernameAndPassword(username, password);
    }
	@Transactional
	public List<Students> getStudentByCourseYear(String courseYear) {
        return studentsRepository.findByCourseYear(courseYear);
    }
	@Transactional
	public List<Students> getStudentByProgramCode(String programCode) {
        return studentsRepository.findByProgramCode(programCode);
    }
	@Transactional

	public Students updateStudents(Long id, Students studentsDetails) {
		Students students = studentsRepository.findById(id).orElse(null);
        if (students != null) {
        	students.setLastname(studentsDetails.getLastname());
        	students.setFirstname(studentsDetails.getFirstname());
        	students.setCourseYear(studentsDetails.getCourseYear());
        	students.setViolationsCount(studentsDetails.getViolationsCount());
            return studentsRepository.save(students);
        }
        return null;
    }
	@Transactional
	public void deleteStudent(Long id) {
		studentsRepository.deleteById(id);
	}
	@Transactional
	public Students createStudent(Students student) {
		return studentsRepository.save(student);
		
	}

}
