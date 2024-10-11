package com.studentsviolation.main.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.studentsviolation.main.entity.ViolationRecords;
import com.studentsviolation.main.service.ViolationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/studentsViolation")
public class StudentsController {

	@Autowired()
	private ViolationService violationService;
	@GetMapping("/homepage")
	public String getLogin(HttpSession session, Model model) {
		String userCode = (String) session.getAttribute("usercode");
		List<ViolationRecords> getViolationByUsercode = violationService.getViolationByUsercode(userCode);
		boolean hasFiveStrikes = false;
		if (getViolationByUsercode!=null) {
		// Loop through all records
			for (ViolationRecords record : getViolationByUsercode) {
			    // Check if the 'strikes' field has a value of 5
			    if (record.getStrike() == 5) {
			        hasFiveStrikes = true; // Mark that we found a record with 5 strikes
			        session.setAttribute("suspended", 5);  // Set the session attribute for 5 strikes
			        break; // Exit the loop early since we prioritize 5
			    }
			}
	
			// After checking for 5, if not found, check for 4
			if (!hasFiveStrikes) {
			    for (ViolationRecords record : getViolationByUsercode) {
			        // Check if the 'strikes' field has a value of 4
			        if (record.getStrike() == 4) {
			            session.setAttribute("suspended", 4);  // Set the session attribute for 4 strikes
			            break; // Exit the loop early since we found 4
			        }
			    }
			}
	
			// Set currentUserViolations for all records
			session.setAttribute("currentUserViolations", getViolationByUsercode);
			return "students/homepage";
		}else {
			return "students/homepage";
		}
	}
	@GetMapping("/getStudentsViolations")
	public String getTotalViolationsMethod(@RequestParam("status")String status, HttpServletRequest request, HttpSession session, Model model) {
		String userCode = (String) session.getAttribute("usercode");
		List<ViolationRecords> getViolationByUsercode = null;
		if (status.equals("compliant")) {
			getViolationByUsercode = violationService.getViolationByUsercodeAndStatus(userCode, 1);
			session.setAttribute("isCompliant", 1);
		}else {
			getViolationByUsercode = violationService.getViolationByUsercodeAndStatus(userCode, 0);
			session.setAttribute("isCompliant", 0);
		}
		session.setAttribute("currentUserViolations", getViolationByUsercode);
		return "students/studentsViolations";
	}
	
	
//	@PostMapping("/studentsLogin")
//	public String loginMethod(@ModelAttribute Students student, HttpSession session, RedirectAttributes redirectAttributes) {
//	    String url = apiLoginUrl + "?txtUserName=" + student.getUsername() + "&txtPassword=" + student.getPassword();
//	    boolean isAuthenticated = apiAuthenticationService.authenticate(url);
//	    String isLogged = "true";
//	    if (isAuthenticated) {
//	        session.setAttribute("isLogged", isLogged);
//	        System.out.println("welcome!!");
//	        return "redirect:/studentsViolation/dashboard";
//	    } else {
//	        redirectAttributes.addFlashAttribute("errorMessage", "Invalid username or password");
//	        return "redirect:/studentsViolation";
//	    }
//	}
}
