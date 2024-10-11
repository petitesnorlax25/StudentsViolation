package com.studentsviolation.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.studentsviolation.main.entity.Notification;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface NotificationRepository extends JpaRepository<Notification, Long>{
	
	@Query(value = "SELECT * FROM Notification ORDER BY id ASC LIMIT 1", nativeQuery = true)
	Notification findFirstRow();

}
