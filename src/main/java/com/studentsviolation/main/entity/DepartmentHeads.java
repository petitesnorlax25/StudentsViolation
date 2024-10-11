package com.studentsviolation.main.entity;

import java.util.List;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class DepartmentHeads {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
}
