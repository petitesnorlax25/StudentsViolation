package com.studentsviolation.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.studentsviolation.main.entity.UserEntity;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AdminRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByUsername(String username);
	UserEntity findByUsernameAndPasswordAndStatus(String username, String Password, int status);
	List<UserEntity> findByPassword(String password);
	UserEntity findByProgramHeadType(String programHeadType);
	UserEntity findByUserType(String userType);
	List<UserEntity> findByStatus(int status);
	
}

