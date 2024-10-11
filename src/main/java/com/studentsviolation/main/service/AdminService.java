package com.studentsviolation.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentsviolation.main.entity.UserEntity;
import com.studentsviolation.main.repository.AdminRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {
	@Autowired
	private AdminRepository adminRepository;
	@Transactional
	public List<UserEntity> getAllAdmins() {
		return adminRepository.findAll();
	}
	@Transactional
	public UserEntity getAdminById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }
	@Transactional
	public UserEntity getAdminByUsername(String username) {
        return adminRepository.findByUsername(username);
    }
	@Transactional
	public UserEntity getAdminByUsernameAndPassword(String username, String password, int status) {
        return adminRepository.findByUsernameAndPasswordAndStatus(username, password, status);
    }
	@Transactional
	public List<UserEntity> getAdminByPassword(String password) {
        return adminRepository.findByPassword(password);
    }
	@Transactional
	public List<UserEntity> getAdminsByStatus(int status) {
        return adminRepository.findByStatus(status);
    }
	@Transactional
	public UserEntity createAdmin(UserEntity admin) {
        return adminRepository.save(admin);
    }
	@Transactional
	public UserEntity getAdminByProgramHeadType(String dept) {
        return adminRepository.findByProgramHeadType(dept);
    }
	@Transactional
	public UserEntity updateAdmin(Long id, UserEntity adminDetails) {
		UserEntity admin = adminRepository.findById(id).orElse(null);
        if (admin != null) {
        	admin.setName(adminDetails.getName());
        	admin.setProgramHeadType(adminDetails.getProgramHeadType());
        	admin.setUsername(adminDetails.getUsername());
        	admin.setPassword(adminDetails.getPassword());
        	admin.setUserType(adminDetails.getUserType());
            return adminRepository.save(admin);
        }
        return null;
    }
	@Transactional
	public void deleteAdmin(Long id) {
	    adminRepository.deleteById(id);
	}

}
