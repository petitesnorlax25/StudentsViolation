package com.studentsviolation.main.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentsviolation.main.DateTime;
import com.studentsviolation.main.ViolationRecordDTO;
import com.studentsviolation.main.entity.AcademicYear;
import com.studentsviolation.main.entity.CrimViolations;
import com.studentsviolation.main.entity.EducViolations;
import com.studentsviolation.main.entity.IsViolations;
import com.studentsviolation.main.entity.Notification;
import com.studentsviolation.main.entity.OaViolations;
import com.studentsviolation.main.entity.Students;
import com.studentsviolation.main.entity.StudentsData;
import com.studentsviolation.main.entity.UserEntity;
import com.studentsviolation.main.entity.ViolationRecords;
import com.studentsviolation.main.entity.Violations;
import com.studentsviolation.main.repository.AcadYearRepository;
import com.studentsviolation.main.repository.AdminRepository;
import com.studentsviolation.main.repository.CrimViolationsRepository;
import com.studentsviolation.main.repository.EducViolationsRepository;
import com.studentsviolation.main.repository.IsViolationsRepository;
import com.studentsviolation.main.repository.NotificationRepository;
import com.studentsviolation.main.repository.OaViolationsRepository;
import com.studentsviolation.main.repository.ViolationRepository;
import com.studentsviolation.main.repository.ViolationsCountRepository;
import com.studentsviolation.main.service.AcadService;
import com.studentsviolation.main.service.AdminService;
import com.studentsviolation.main.service.ApiAuthenticationService;
import com.studentsviolation.main.service.DepartmentViolationsService;
import com.studentsviolation.main.service.NotificationService;
import com.studentsviolation.main.service.StudentsService;
import com.studentsviolation.main.service.ViolationService;
import com.studentsviolation.main.service.ViolationsCountService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;

@Controller
@RequestMapping("/studentsViolation")
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private ViolationService violationService;
	@Autowired
	private ViolationsCountService violationsCountService;
	@Autowired
	private ViolationsCountRepository violationsCountRepository;
	@Autowired
	private StudentsService studentsService;
	@Autowired
	private ViolationRepository violationRepository;
	@Autowired
	private AcadYearRepository acadYearRepository;
	@Autowired
	private AcadService acadService;
	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private DepartmentViolationsService departmentViolationsService;
	@Autowired
	private IsViolationsRepository isViolationsRepository;
	@Autowired
	private OaViolationsRepository oaViolationsRepository;
	@Autowired
	private CrimViolationsRepository crimViolationsRepository;
	@Autowired
	private EducViolationsRepository educViolationsRepository;
	@Value("${api.login.url}")
    private String apiLoginUrl;

    private final ApiAuthenticationService apiAuthenticationService;

    public AdminController(ApiAuthenticationService apiAuthenticationService) {
        this.apiAuthenticationService = apiAuthenticationService;
    }
	
	@GetMapping("/register")
	public String showRegistration(Model model, HttpSession session) {	
		return "users/registration";
		
	}
	@GetMapping
	public String showLogin(Model model, HttpSession session) {
		return "users/signin";
		
	}
	@GetMapping("/studentsLogin")
	public String showStudentsLogin(Model model, HttpSession session) {
		return "users/students/studentsLogin";
		
	}
	@GetMapping("/dashboard")
	public String showDashboard(Model model, HttpSession session) {
		String isLogged = (String) session.getAttribute("isLogged");
		UserEntity currentUser = (UserEntity) session.getAttribute("currentUser");
		Notification getNotification = new Notification();
		if (currentUser == null) {
			return "redirect:/studentsViolation?unauthorizedAccess=You are not signed in, Please Log-in!";
		}
		try {
			getNotification = notificationRepository.findFirstRow();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		session.setAttribute("getNotification", getNotification);
		return "users/dashboard";
		
	}
	@PostMapping("/registration")
	public String registration(@RequestParam("addUser") String isAdd, HttpServletRequest request, Model model,
			 @RequestParam("image")MultipartFile file) throws IOException, SQLException  {
		UserEntity user = new UserEntity();
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String userType = request.getParameter("userType");
		String programCode = request.getParameter("programCode");
	       byte[] bytes = file.getBytes();
	        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
	 
		if (isAdd==null) {	
	
			
			UserEntity usernameFromDb = adminService.getAdminByUsername(username);
			String url = apiLoginUrl + "?txtUserName=" + username + "&txtPassword=" + password;
//		    Students students = new Students();
		    StudentsData studentData = apiAuthenticationService.authenticate(url);
		    if (studentData != null && studentData.isLogin()) {
		    	return "redirect:/studentsViolation/register?errorMessage=can't register! students should go to log in page";
		    }
			if (usernameFromDb!=null) {
				return "redirect:/studentsViolation?errorMessage=Username is already taken!";
			}
	 
	        user.setUserType("admin");
	        user.setStatus(1);
	        user.setImage(blob);
	        user.setPassword(password);
	        user.setUsername(username);
	        user.setName(name);
			adminService.createAdmin(user);
			return "redirect:/studentsViolation";
		}
		user.setUserType(userType);
		user.setProgramHeadType(programCode);
	    user.setImage(blob);
		user.setStatus(1);
		user.setPassword(password);
        user.setUsername(username);
        user.setName(name);
		adminService.createAdmin(user);
		return "redirect:/studentsViolation/users";
		
		
	}
	@GetMapping("/check-username")
	@ResponseBody
	public boolean checkUsername(@RequestParam("username") String username, Model model) {
		UserEntity getUserByUsername = adminService.getAdminByUsername(username);
		return getUserByUsername!=null;
	}
	@PostMapping("/studentsLogin")
	public String studentsLoginMethod(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String url = apiLoginUrl + "?txtUserName=" + username + "&txtPassword=" + password;
	    Students students = new Students();
	    StudentsData studentData = apiAuthenticationService.authenticate(url);
	    String firstname = studentData.getFirst_name();	 
        String middlename = studentData.getMiddle_name();	
        String lastname = studentData.getLast_name();
        UserEntity getDepthead = new UserEntity();
	    UserEntity getDisc = adminRepository.findByUserType("disciplinaryofficer");
	    UserEntity getGuide = adminRepository.findByUserType("guidancecounselor");
	    Students getStudentFromDB = studentsService.getStudentByFirstnameAndMiddlenameAndLastname(firstname, middlename, lastname);
	    if (getStudentFromDB==null&&studentData!=null) {
	    	String description = studentData.getProgram_description();
        	String[] words = description.split(" ");

        	// Join the first three words
        	String firstThreeWords = String.join(" ", words[0], words[1], words[2]);

        	System.out.println(firstThreeWords);
        	if (firstThreeWords.equals("BACHELOR OF ARTS")) {
        		students.setProgramCode("OA");
        	}else if (firstThreeWords.equals("BACHELOR OF ELEMENTARY")|| firstThreeWords.equals("BACHELOR OF PHYSICAL")||firstThreeWords.equals("BACHELOR OF SECONDARY")) {
        		students.setProgramCode("EDUC");
        	}else {
        		students.setProgramCode(studentData.getProgram_code());
        	}
            students.setFirstname(studentData.getFirst_name());
            students.setMiddlename(studentData.getMiddle_name());
            students.setLastname(studentData.getLast_name());
            students.setYear(studentData.getYear_level());
            students.setPassword(password);
            students.setUsername(studentData.getUser_code());
            students.setSection(studentData.getSection());
            students.setCourseYear(studentData.getYear_level() + studentData.getSection());
            students.setProgram_description(studentData.getProgram_description());
            students.setViolationsCount(0);
            // Logging the student data
            
            try {
                studentsService.createStudent(students);
            } catch (Exception e) {
                System.err.println("Error creating student: " + e.getMessage());
                e.printStackTrace();
            }
            return "redirect:/studentsViolation/getHomepage";
	    }else if(getStudentFromDB==null&&studentData==null){
	    	 return "redirect:/studentsViolation?errorMessage=invalid username or password";
	    }else {
	    	Students getStudent = studentsService.getStudentByUsernameAndPassword(username, password);
	    	getStudent = (getStudent != null) ? getStudent : new Students(); 
	    	if (!getStudent.getUsername().equals("username")&&!getStudent.getPassword().equals("password")) {
	    		
	    		return "redirect:/studentsViolation?errorMessage=invalid username or password";
	    	}
	    }
	    System.out.println("Creating student with data: " + students);
        session.setAttribute("isLogged", "true");
        System.out.println("afgassasdhgfdtg: " + (studentData.getFirst_name() != null ? studentData.getFirst_name() : "N/A"));
        model.addAttribute("students", students); // Store student data in session if needed
        session.setAttribute("name", (studentData.getFirst_name() != null ? studentData.getFirst_name() : "") + "\n" + (studentData.getLast_name() != null ? studentData.getLast_name() : ""));
        session.setAttribute("courseYear", 
        	    studentData.getYear_level() + "\n" + 
        	    (studentData.getSection() != null ? studentData.getSection() : ""));
        session.setAttribute("program", studentData.getProgram_description());
        session.setAttribute("usercode", studentData.getUser_code());
        List<ViolationRecords> getViolationsByStudent = new ArrayList<>();
        List<ViolationRecords> getViolationsByStudentAndStatus = new ArrayList<>();
        if (getStudentFromDB != null) {
        	 switch(getStudentFromDB.getProgramCode()) {
         	case "IS":
         		getDepthead = adminRepository.findByProgramHeadType("IS");
         		break;
         	case "OA":
         		getDepthead = adminRepository.findByProgramHeadType("OA");

         		break;
         	case "CRIM":
         		getDepthead = adminRepository.findByProgramHeadType("CRIM");

         		break;
         	case "EDUC":
         		getDepthead = adminRepository.findByProgramHeadType("EDUC");
         		
         		break;
         		
         	}
         	session.setAttribute("departmentHead", getDepthead);
         	session.setAttribute("disc", getDisc);
         	session.setAttribute("guide", getGuide);
            getViolationsByStudent = violationService.getViolationByStudentsId(getStudentFromDB.getId());
            getViolationsByStudentAndStatus = violationService.getViolationByUsercodeAndStatus(getStudentFromDB.getUsername(), 0);
        }
    	session.setAttribute("departmentHead", getDepthead);
    	session.setAttribute("disc", getDisc);
    	session.setAttribute("guide", getGuide);
        long countRow1 = getViolationsByStudentAndStatus != null ? getViolationsByStudentAndStatus.size() : 0;
        long countRow = getViolationsByStudent != null ? getViolationsByStudent.size() : 0;
        System.out.println("rowssL "+countRow);
        session.setAttribute("violationsTotal", countRow);
        session.setAttribute("violationsCurrentTotal", countRow1);
        return "redirect:/studentsViolation/getHomepage";
	}
	@PostMapping("/login")
	public String loginMethod(@ModelAttribute UserEntity admin, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
	    int status = 1;
	    UserEntity validateAdmin = adminService.getAdminByUsernameAndPassword(admin.getUsername(), admin.getPassword(), status);
	    Students students = new Students();

	    
	    //String getNotif = (String) session.getAttribute("notif");
	    if (validateAdmin == null) {
	    	return "redirect:/studentsViolation?errorMessage=Invalid username or password";
	    } else if (validateAdmin != null) {
	        System.out.println(validateAdmin.getUserType()+validateAdmin.getUserType()+validateAdmin.getUserType()+validateAdmin.getUserType());
	        AcademicYear currentAcad = acadYearRepository.findByStatus(1);
	        if ("departmenthead".equals(validateAdmin.getUserType())) {
	        	if (validateAdmin.getProgramHeadType().equals("IS")) {
	        		session.setAttribute("isDeptHead", "true");
	        	}
	            session.setAttribute("isDeptHead", "true");
	        }
	        try {
		        List<ViolationRecords> getAllRecords = violationService.getAllViolations();
		        List<ViolationRecords> getAllISRecords = violationService.getViolationByCourse("IS");
		        List<ViolationRecords> getAllCRIMRecords = violationService.getViolationByCourse("CRIM");
		        List<ViolationRecords> getAllARTSRecords = violationService.getViolationByCourse("ARTS");
		        List<ViolationRecords> getAllEDUCRecords = violationService.getViolationByCourse("EDUC");
		        List<Students> getAllStudents = studentsService.getAllStudents();
		        List<Students> getISStudents = studentsService.getStudentByProgramCode("IS");
		        List<Students> getCRIMStudents = studentsService.getStudentByProgramCode("CRIM");
		        List<Students> getARTSStudents = studentsService.getStudentByProgramCode("ARTS");
		        List<Students> getEDUCStudents = studentsService.getStudentByProgramCode("EDUC");
		       
		        int countRecords = getAllRecords != null ? getAllRecords.size() : 0;
		        int countStudents = getAllStudents != null ? getAllStudents.size() : 0;
		        int countISRecords = getAllISRecords != null ? getAllISRecords.size() : 0;
		        int countARTSRecords = getAllARTSRecords != null ? getAllARTSRecords.size() : 0;
		        int countCRIMRecords = getAllCRIMRecords != null ? getAllCRIMRecords.size() : 0;
		        int countEDUCRecords = getAllEDUCRecords != null ? getAllEDUCRecords.size() : 0;
		        int countISStudents = getISStudents != null ? getISStudents.size() : 0;
		        int countARTSStudents = getARTSStudents != null ? getARTSStudents.size() : 0;
		        int countEDUCStudents = getEDUCStudents != null ? getEDUCStudents.size() : 0;
		        int countCRIMStudents = getCRIMStudents != null ? getCRIMStudents.size() : 0;
		        session.setAttribute("countStudents", countStudents);
		        session.setAttribute("countISRecords", countISRecords);
		        session.setAttribute("countARTSRecords", countARTSRecords);
		        session.setAttribute("countCRIMRecords", countCRIMRecords);
		        session.setAttribute("countEDUCRecords", countEDUCRecords);
		        session.setAttribute("countISStudents", countISStudents);
		        session.setAttribute("countARTSStudents", countARTSStudents);
		        session.setAttribute("countEDUCStudents", countEDUCStudents);
		        session.setAttribute("countCRIMStudents", countCRIMStudents);
		        
		        List<Object[]> programCodeViolations = violationRepository.findTotalViolationsByProgramCode();
		        programCodeViolations = (programCodeViolations != null) ? programCodeViolations : new ArrayList<>();
	
			    	for (Object[] violation : programCodeViolations) {
			    	    System.out.println("Program Code: " + violation[0] + ", Violations: " + violation[1]);
			    	}
			    	
			    	model.addAttribute("programCodeViolations", programCodeViolations);
	
		        session.setAttribute("currentAcad", currentAcad);
		        session.setAttribute("countRecords", countRecords);
		        
		        session.setAttribute("currentUser", validateAdmin);
		        session.setAttribute("isLogged", "true");
		        
		        session.setAttribute("userType", validateAdmin.getUserType());
		        System.out.println("welcome user!");
	        }catch (Exception e) {
	        	System.err.println(e.getMessage());
	        	return "redirect:/studentsViolation?errorMessage=error occured";
	        }

	    }
	    return "redirect:/studentsViolation/dashboard";
	}
	@GetMapping("/programCodeViolations")
	public ResponseEntity<List<Object[]>> getProgramCodeViolations(HttpSession session, Model model) {
		UserEntity currentUser = (UserEntity) session.getAttribute("currentUser");
		List<Object[]> results;
	    try {
	        if (currentUser.getUserType().equals("departmenthead")) {
	        	results = violationRepository.findTotalViolationsByProgramCodeAndGroupByYearLevel(currentUser.getProgramHeadType());
		    	
	        }else {
	        	results = violationRepository.findTotalViolationsByProgramCode();
	        }
	        return ResponseEntity.ok(results);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}



	@GetMapping("/violationsCount")
	public ResponseEntity<List<?>> getViolationsCount(HttpSession session) {
	    UserEntity currentUser = (UserEntity) session.getAttribute("currentUser");
	    List<?> countList = null;
	    
	    try {
	        if (currentUser == null) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	        }
	        System.out.println("currentUser : "+currentUser.getUserType()+currentUser.getName());

	        if ("departmenthead".equals(currentUser.getUserType())) {
	            switch (currentUser.getProgramHeadType()) {
	                case "IS":
	                    countList = isViolationsRepository.findAll();
	                    break;
	                case "OA":
	                    countList = oaViolationsRepository.findAll();
	                    break;
	                case "CRIM":
	                    countList = crimViolationsRepository.findAll();
	                    break;
	                case "EDUC":
	                    countList = educViolationsRepository.findAll();
	                    break;
	                default:
	                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	            }
	        } else {
	            countList = violationsCountRepository.findAll();
	        }
	        System.out.println("Fetched violation counts: " + countList);

	        if (countList.isEmpty()) {
	            return ResponseEntity.noContent().build(); // 204 No Content
	        }


	        return ResponseEntity.ok(countList);
	    } catch (Exception e) {
	        e.printStackTrace(); // Ideally replace with logging
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}


	@PostMapping("/post-controller")
    @ResponseBody
    public ResponseEntity<String> postProgram(@RequestParam("program") String program, @RequestParam("id") Long id, HttpSession session) {
        try {
      
        	
            if (program == null || program.isEmpty()) {
                throw new IllegalArgumentException("Program selection cannot be empty");
            }
            UserEntity getUser = adminService.getAdminById(id);
        	getUser.setProgramHeadType(program);
        	adminService.updateAdmin(id, getUser);
        
            System.out.println("Program selected: " + program);
            String notif = (String) session.getAttribute("hasNotif");
            System.out.println(notif+notif);
            session.setAttribute("currentUser", getUser);
            session.setAttribute("hasNotif", notif);
            return ResponseEntity.ok("Program " + program + " has been selected successfully!");

        } catch (Exception e) {

            System.err.println("Error selecting program: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to select program: " + e.getMessage());
        }
    }
//	 @GetMapping("/testing")
//	    public String getViolations(Model model) {
//	        List<Object[]> programCodeViolations = violationRepository.findTotalViolationsByProgramCode();
//	        model.addAttribute("programCodeViolations", programCodeViolations);
//	        return "users/testing"; // Thymeleaf template name
//	 }



	@PostMapping("/logout")
	public String logoutMethod(HttpSession session, HttpServletRequest request) {
		String notif = (String) session.getAttribute("notif");
		session.invalidate();
		session = request.getSession(true);  
		session.setAttribute("notif", notif);
		return "redirect:/studentsViolation";
	}
	@RequestMapping(value = {"/registration", "/login", "/updateUser", "/softDeleteUser", "/hardDeleteUser", "/restoreDeletedUser"}, method = RequestMethod.GET)
    public String handlePostDirectAccess() {
        // Redirect to the registration form if accessed directly
		return "redirect:/studentsViolation";
    }
	@GetMapping("/users") 
	public String getUsers (HttpSession session, Model Model, HttpServletRequest request) {
		String isLogged = (String) session.getAttribute("isLogged");
		UserEntity currentUser = (UserEntity) session.getAttribute("currentUser");
		if (currentUser == null) {
			return "redirect:/studentsViolation?unauthorizedAccess=You are not signed in, Please Log-in!";
		}
		List<UserEntity> getAllUsers = adminService.getAllAdmins();
		Model.addAttribute("users", getAllUsers);
		session.setAttribute("currentUser", currentUser);
		return "users/users";

		
	}
	@GetMapping("/violators") 
	public String getViolators (HttpSession session, Model Model, HttpServletRequest request) {

		String isLogged = (String) session.getAttribute("isLogged");
		UserEntity currentUser = (UserEntity) session.getAttribute("currentUser");
		Notification getNotification = notificationRepository.findFirstRow();
		if (currentUser == null) {
			return "redirect:/studentsViolation?unauthorizedAccess=You are not signed in, Please Log-in!";
		}
		String hasNotif = (String) session.getAttribute("hasNotif");
        if (hasNotif != null) {
            session.removeAttribute("hasNotif");
        }
		if (currentUser.getUserType().equals("departmenthead")) {
			if (currentUser.getProgramHeadType()!=null) {
				System.out.println(currentUser.getProgramHeadType());
				switch (currentUser.getProgramHeadType()) {
			    case "IS":
			    	List<ViolationRecords> isRecords = violationService.getViolationRecordsByProgramCodeAndDeptHeadApproval("IS", 0);
			    	List<ViolationRecords> isCompRecords = violationService.getViolationRecordsByProgramCodeAndDeptHeadApproval("IS", 1);
			    	isCompRecords.sort(Comparator.comparing(ViolationRecords::getViolationDate).reversed());
			    	isRecords.sort(Comparator.comparing(ViolationRecords::getViolationDate).reversed());
			    	getNotification.setIsNotif(0);
			    	
			    	System.out.println("aheheheh");
			    	session.setAttribute("violators", isRecords);
			    	session.setAttribute("compViolators", isCompRecords);
			        break; // Exits the switch block
			    case "CRIM":
			    	List<ViolationRecords> crimRecords = violationService.getViolationRecordsByProgramCodeAndDeptHeadApproval("CRIM", 0);
			    	List<ViolationRecords> crimCompRecords = violationService.getViolationRecordsByProgramCodeAndDeptHeadApproval("CRIM", 1);
			    	crimRecords.sort(Comparator.comparing(ViolationRecords::getViolationDate).reversed());
			    	crimCompRecords.sort(Comparator.comparing(ViolationRecords::getViolationDate).reversed());
			    	getNotification.setCrinNotif(0);
			    	session.setAttribute("violators", crimRecords);
			    	session.setAttribute("compViolators", crimCompRecords);
			        break; // Exits the switch block
			    case "OA":
			    	List<ViolationRecords> artsRecords = violationService.getViolationRecordsByProgramCodeAndDeptHeadApproval("OA", 0);
			    	List<ViolationRecords> artsCompRecords = violationService.getViolationRecordsByProgramCodeAndDeptHeadApproval("OA", 1);
			    	artsRecords.sort(Comparator.comparing(ViolationRecords::getViolationDate).reversed());
			    	artsCompRecords.sort(Comparator.comparing(ViolationRecords::getViolationDate).reversed());
			    	getNotification.setOaNotif(0);
			    	session.setAttribute("violators", artsRecords);
			    	session.setAttribute("compViolators", artsCompRecords);
			        break; // Exits the switch block
			    case "EDUC":
			    	List<ViolationRecords> educRecords = violationService.getViolationRecordsByProgramCodeAndDeptHeadApproval("EDUC", 0);
			    	List<ViolationRecords> eudcCompRecords = violationService.getViolationRecordsByProgramCodeAndDeptHeadApproval("EDUC", 1);
			    	educRecords.sort(Comparator.comparing(ViolationRecords::getViolationDate).reversed());
			    	eudcCompRecords.sort(Comparator.comparing(ViolationRecords::getViolationDate).reversed());
			    	getNotification.setEducNotif(0);
			    	session.setAttribute("violators", educRecords);
			    	session.setAttribute("compViolators", eudcCompRecords);
			        break; // Exits the switch block
			    default:
			        break; // Exits the switch block
				}
			}
			notificationService.updateNotif(getNotification.getId(), getNotification);
			return "users/violators";
		}else if (currentUser.getUserType().equals("disciplinaryofficer")) {
			List<ViolationRecords> records = violationRepository.findViolationRecordsByDisciplinaryOfficerApproval(0);
			List<ViolationRecords> compRecords = violationRepository.findViolationRecordsByDisciplinaryOfficerApproval(1);
			records.sort(Comparator.comparing(ViolationRecords::getViolationDate).reversed());
			compRecords.sort(Comparator.comparing(ViolationRecords::getViolationDate).reversed());
			getNotification.setDiscNotif(0);
			session.setAttribute("violators", records);
			session.setAttribute("compViolators", compRecords);
		}else if (currentUser.getUserType().equals("guidancecounselor")) {
			getNotification.setGuideNotif(0);
			List<ViolationRecords> records = violationRepository.findViolationRecordsByGuidanceOfficerApproval(0);
			List<ViolationRecords> compRecords = violationRepository.findViolationRecordsByGuidanceOfficerApproval(1);
			records.sort(Comparator.comparing(ViolationRecords::getViolationDate).reversed());
			compRecords.sort(Comparator.comparing(ViolationRecords::getViolationDate).reversed());
	    	session.setAttribute("violators", records);
	    	session.setAttribute("compViolators", compRecords);
		}
		
		notificationService.updateNotif(getNotification.getId(), getNotification);
		return "users/violators";

		
	}
	@GetMapping("/students") 
	public String getStudents (HttpSession session, Model Model, HttpServletRequest request) {
		String isLogged = (String) session.getAttribute("isLogged");
		UserEntity currentUser = (UserEntity) session.getAttribute("currentUser");
		
		if (currentUser == null) {
			return "redirect:/studentsViolation?unauthorizedAccess=You are not signed in, Please Log-in!";
		}
		if (currentUser.getUserType().equals("departmenthead")) {
			System.out.println(currentUser.getProgramHeadType());
			switch (currentUser.getProgramHeadType()) {
		    case "IS":
		    	List<Students> getIsStudents = studentsService.getStudentByProgramCode("IS");
		    	System.out.println("students");
		    	session.setAttribute("students", getIsStudents);
		        break; // Exits the switch block
		    case "CRIM":
		    	List<Students> getCrimStudents = studentsService.getStudentByProgramCode("CRIM");
		    	session.setAttribute("students", getCrimStudents);
		        break; // Exits the switch block
		    case "ARTS":
		    	List<Students> getArtsStudents = studentsService.getStudentByProgramCode("ARTS");
		    	session.setAttribute("students", getArtsStudents);
		        break; // Exits the switch block
		    case "EDUC":
		    	List<Students> getEducStudents = studentsService.getStudentByProgramCode("EDUC");
		    	session.setAttribute("students", getEducStudents);
		        break; // Exits the switch block
		    default:
		        break; // Exits the switch block
			}

		}
		session.setAttribute("currentUser", currentUser);
		return "users/violationsHistory";

		
	}
	@GetMapping("/records")
	public String getRecordsMethod(Model model, HttpSession session) {
		String isLogged = (String) session.getAttribute("isLogged");
		UserEntity currentUser = (UserEntity) session.getAttribute("currentUser");
		List<ViolationRecords> getAllRecords = violationService.getAllViolations();
		List<ViolationRecords> getAllRecordsByStatus = violationService.getViolationByStatus(1);
		if (currentUser == null) {
			
			return "redirect:/studentsViolation?unauthorizedAccess=You are not signed in, Please Log-in!";
		}
		getAllRecords.sort(Comparator.comparing(ViolationRecords::getViolationDate).reversed());
		model.addAttribute("violationRecords",getAllRecords);
		session.setAttribute("currentUser", currentUser);
		session.setAttribute("compViolationRecords", getAllRecordsByStatus);
		return "users/records";
		
	}
	@GetMapping("/getViolationsByDepartment")
	public String getRecordsByDepartmentMethod(@RequestParam("department") String department, Model model, HttpSession session) {
		String isLogged = (String) session.getAttribute("isLogged");
		UserEntity currentUser = (UserEntity) session.getAttribute("currentUser");
		List<ViolationRecords> getAllRecords = violationService.getAllViolations();
		if (currentUser == null) {
			
			return "redirect:/studentsViolation?unauthorizedAccess=You are not signed in, Please Log-in!";
		}
		if (department.equals("crim")) {
			List<ViolationRecords> getCrimRecords = violationService.getViolationByCourse("CRIM");
			getCrimRecords.sort(Comparator.comparing(ViolationRecords::getViolationDate).reversed());
			model.addAttribute("violationRecords", getCrimRecords);
		}else if (department.equals("is")) {
			List<ViolationRecords> getIsRecords = violationService.getViolationByCourse("IS");
			getIsRecords.sort(Comparator.comparing(ViolationRecords::getViolationDate).reversed());
			model.addAttribute("violationRecords", getIsRecords);
		}else if (department.equals("educ")) {
			List<ViolationRecords> getEducRecords = violationService.getViolationByCourse("EDUC");
			getEducRecords.sort(Comparator.comparing(ViolationRecords::getViolationDate).reversed());
			model.addAttribute("violationRecords", getEducRecords);
		}else if (department.equals("arts")) {
			List<ViolationRecords> getArtsRecords = violationService.getViolationByCourse("ARTS");
			getArtsRecords.sort(Comparator.comparing(ViolationRecords::getViolationDate).reversed());
			model.addAttribute("violationRecords", getArtsRecords);
		}
		session.setAttribute("currentUser", currentUser);
		return "users/records";
	}
	@GetMapping("/studentEntry")
	public String getStudentEntryMethod(Model model, HttpSession session) {
		List<ViolationRecords> allViolation = violationService.getAllViolations();
		String isLogged = (String) session.getAttribute("isLogged");
		UserEntity currentUser = (UserEntity) session.getAttribute("currentUser");
		if (currentUser == null) {
			return "redirect:/studentsViolation?unauthorizedAccess=You are not signed in, Please Log-in!";
		}
		allViolation.sort(Comparator.comparing(ViolationRecords::getViolationDate).reversed());
		model.addAttribute("violations", allViolation);
		return "users/studentEntry";
	}
	@GetMapping("/academicYear")
	public String getAcademicYearMethod(Model model, HttpSession session) {
		List<AcademicYear> getAllAcad = acadYearRepository.findAll();
		String isLogged = (String) session.getAttribute("isLogged");
		UserEntity currentUser = (UserEntity) session.getAttribute("currentUser");
		if (currentUser == null) {
			return "redirect:/studentsViolation?unauthorizedAccess=You are not signed in, Please Log-in!";
		}
		session.setAttribute("currentUser", currentUser);
		model.addAttribute("acadYearAll", getAllAcad);
		return "users/academicYear";
		
	}
	
	@GetMapping("/reports")
	public String getReportsMethod(Model model, HttpSession session) {
		String isLogged = (String) session.getAttribute("isLogged");
		UserEntity currentUser = (UserEntity) session.getAttribute("currentUser");
		if (currentUser == null) {
			return "redirect:/studentsViolation?unauthorizedAccess=You are not signed in, Please Log-in!";
		}
		session.setAttribute("currentUser", currentUser);
		return "users/reports";
		
	}
	@GetMapping("/getReports")
	public String getDiffReportsMethod(@RequestParam("department")String department, Model model, HttpSession session) {
		List<ViolationRecords> getViolationRecordsByDept = violationService.getViolationByCourse(department);
		getViolationRecordsByDept.sort(Comparator.comparing(ViolationRecords::getViolationDate).reversed());
		model.addAttribute("violationRecords", getViolationRecordsByDept);
		
		UserEntity getDeptHead = new UserEntity();
		switch(department) {
		case"CRIM":
			getDeptHead = adminService.getAdminByProgramHeadType("CRIM");
			break;
		case"EDUC":
			getDeptHead = adminService.getAdminByProgramHeadType("EDUC");
			break;
		case"OA":
			getDeptHead = adminService.getAdminByProgramHeadType("OA");
			break;
		case"IS":
			getDeptHead = adminService.getAdminByProgramHeadType("IS");
			break;
		}
		session.setAttribute("getDeptHead", getDeptHead.getName());
		return "users/reports";
		
	}
	@GetMapping("/userAccount")
	public String getUserAccount(Model model, HttpSession session) {
		String isLogged = (String) session.getAttribute("isLogged");
		UserEntity currentUser = (UserEntity) session.getAttribute("currentUser");
		if (currentUser == null) {
			return "redirect:/studentsViolation?unauthorizedAccess=You are not signed in, Please Log-in!";
		}
		UserEntity users = (UserEntity) session.getAttribute("currentUser");
		model.addAttribute("currentUser", users);
		System.out.println(users.getName());
		session.setAttribute("currentUser", currentUser);
		return "users/profile";
		
	}
	@PostMapping("/editProfile")
	public String editProfileMethod(Model model, HttpServletRequest request, HttpSession session, 
			@RequestParam("image")MultipartFile image) throws IOException, SQLException {
		
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String oldUsername = request.getParameter("oldUsername");
		UserEntity getOldUser = adminService.getAdminByUsername(oldUsername);
		if (getOldUser!=null) {
			getOldUser.setName(name);
			getOldUser.setUsername(username);
			getOldUser.setPassword(password);
			if (image != null && !image.isEmpty()) {
	            byte[] bytes = image.getBytes();
	            Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
	            getOldUser.setImage(blob);

	        }
			adminService.updateAdmin(getOldUser.getId(), getOldUser);
			session.setAttribute("currentUser", getOldUser);
			return "redirect:/studentsViolation/userAccount";
		}
		return "redirect:/studentsViolation/userAccount?errorMessage=error updating profile";
	    
		
	}
	@PostMapping("/updateUser")
	@ResponseBody
	public String updateUser(@RequestParam("id") Long id,
	                         @RequestParam("fullname") String name,
	                         @RequestParam("username") String username,
	                         @RequestParam("password") String password,
	                         @RequestParam("userType") String userType,
	                         @RequestParam(value = "image", required = false) MultipartFile image)  throws IOException, SQLException {
		try {
			UserEntity existingAdmin = adminService.getAdminById(id);
		    if (existingAdmin != null) {
		        existingAdmin.setName(name);
		        existingAdmin.setUsername(username);
		        existingAdmin.setPassword(password);
		        existingAdmin.setUserType(userType);

		        if (image != null && !image.isEmpty()) {
	                byte[] bytes = image.getBytes();
	                Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
	                existingAdmin.setImage(blob);
	
	            }
		        adminService.updateAdmin(id, existingAdmin);
		        return "success";
		    }
		}catch (Exception e) {
			return "error"+e.getMessage();
		}
		return "Error";
	    
	}
	@PostMapping("/softDeleteUser")
	public String softDeleteUsersMethod(@ModelAttribute UserEntity user, HttpSession session, Model model) {
		Long id = user.getId();
		UserEntity getUser = adminService.getAdminById(id);
		if (getUser!=null) {
			getUser.setStatus(0);
			adminService.updateAdmin(id, getUser);
			return "redirect:/studentsViolation/users?status=enabled";
		}
		return "redirect:/studentsViolation/users?status=enabled";
	}
	@PostMapping("/hardDeleteUser")
	public String hardDeleteUsersMethod(@ModelAttribute UserEntity user, HttpSession session, Model model) {
		Long id = user.getId();
		adminService.deleteAdmin(id);
		
		return "redirect:/studentsViolation/users?status=disabled";

	}
	@PostMapping("/restoreDeletedUser")
	public String restoreDeletedUsersMethod(@ModelAttribute UserEntity user, HttpSession session, Model model) {
		Long id = user.getId();
		UserEntity getUser = adminService.getAdminById(id);
		if (getUser!=null) {
			getUser.setStatus(1);
			adminService.updateAdmin(id, getUser);
			return "redirect:/studentsViolation/users?status=disabled";
		}
		return "redirect:/studentsViolation/users?status=disabled";

	}
	 @GetMapping("/display")
	 @Transactional
	    public ResponseEntity<byte[]> displayImage(@RequestParam long id) throws IOException, SQLException {
	    	UserEntity image = adminService.getAdminById(id);
	    	byte[] imageBytes = null;
	        imageBytes = image.getImage().getBytes(1, (int) image.getImage().length());
	        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);

	       
	        
	    }
	    @PostMapping("/addViolation")
	    public String addViolationMethod(@ModelAttribute Students student, HttpServletRequest request, Model model, HttpSession session) {
	        Students getStudent = studentsService.getStudentByUsername(student.getUsername());
	        AcademicYear getAcadByStatus = acadYearRepository.findByStatus(1);
	        String violationType = request.getParameter("violationType");
	        Violations getViolation = violationsCountService.getViolationByViolationName(violationType);
	        IsViolations getIsViolation = departmentViolationsService.getIsViolationByViolationName(violationType);
	        OaViolations getOaViolation = departmentViolationsService.getOaViolationByViolationName(violationType);
	        CrimViolations getCrimViolation = departmentViolationsService.getCrimViolationByViolationName(violationType);
	        EducViolations getEducViolation = departmentViolationsService.getEducViolationByViolationName(violationType);
	        Notification getNotif = notificationRepository.findFirstRow();
	        // Ensure the student is valid (your existing check)
	        if (getStudent == null) {
	            return "redirect:/studentsViolation/studentEntry?errorMessage=wrong user code";
	        }

	        // If the violation does not exist, create a new one
	        if (getViolation == null) {
	            // Create a new violation
	            getViolation = new Violations();
	            Long count = 1L; // Start with a count of 1
	            getViolation.setCount(count);
	            getViolation.setViolationName(violationType);
	            
	            // Save the new violation to the database (you will need to handle saving logic)
	            violationsCountRepository.save(getViolation);  // Assuming you have a save method in service layer
	        } else {
	            // If the violation exists, increment the count
	            Long getCount = getViolation.getCount();
	            getViolation.setCount(getCount + 1);
	            
	            // Update the existing violation in the database
	            violationsCountService.updateViolationsCount(getViolation.getId(), getViolation);  // Assuming you have a save method
	        }

	        // Get the latest violation record by date
	        Optional<ViolationRecords> latestViolation = violationService.getViolationByStudentsId(getStudent.getId())
	            .stream()
	            .max(Comparator.comparing(v -> {
	                try {
	                    SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM, yyyy, HH:mm:ss");
	                    return dateFormat.parse(v.getViolationDate());
	                } catch (ParseException e) {
	                    throw new RuntimeException(e); // Handle parse exception
	                }
	            }));

	        ViolationRecords violation = new ViolationRecords();
	        long strike = 1; // Default to first strike

	        if (latestViolation.isPresent()) {
	            ViolationRecords latest = latestViolation.get();
	            strike = latest.getStrike(); // Get the strike level from the latest violation
	            
	            // Increment the strike and determine the corresponding sanction
	            switch ((int) strike) {
	                case 1:
	                    violation.setSanction("CommunityService");
	                    strike = 2;
	                    break;
	                case 2:
	                    violation.setSanction("CommunityService+fines");
	                    strike = 3;
	                    break;
	                case 3:
	                    violation.setSanction("Suspension");
	                    strike = 4;
	                    break;
	                case 4:
	                default:
	                    violation.setSanction("Expulsion");
	                    strike = 5;
	                    break;
	            }
	        } else {
	            // No previous violations, set initial values
	            violation.setSanction("CommunityService");
	        }
	        
	   
	        	//Notification notification = new Notification();

		        switch(getStudent.getProgramCode()) {
		        	case "IS":
		        		if (getIsViolation == null) {
		        			getIsViolation = new IsViolations();
		    	            Long count = 1L; // Start with a count of 1
		    	            getIsViolation.setCount(count);
		    	            getIsViolation.setViolationName(violationType);
		    	            
		    	            // Save the new violation to the database (you will need to handle saving logic)
		    	            isViolationsRepository.save(getIsViolation);  // Assuming you have a save method in service layer
		    	        } else {
		    	            // If the violation exists, increment the count
		    	            Long getCount = getIsViolation.getCount();
		    	            getIsViolation.setCount(getCount + 1);
		    	        }
		        		int isInt = getNotif.getIsNotif()+1;
		        		getNotif.setIsNotif(isInt);
		        		break;
		        	case "CRIM":
		        		if (getCrimViolation == null) {
		        			getCrimViolation = new CrimViolations();
		    	            Long count = 1L; // Start with a count of 1
		    	            getCrimViolation.setCount(count);
		    	            getCrimViolation.setViolationName(violationType);
		    	            
		    	            // Save the new violation to the database (you will need to handle saving logic)
		    	            crimViolationsRepository.save(getCrimViolation);  // Assuming you have a save method in service layer
		    	        } else {
		    	            // If the violation exists, increment the count
		    	            Long getCount = getCrimViolation.getCount();
		    	            getCrimViolation.setCount(getCount + 1);
		    	        }
		        		int crimInt = getNotif.getCrinNotif()+1;
		        		getNotif.setCrinNotif(crimInt);
		        		break;
		        	case "OA":
		        		if (getOaViolation == null) {
		        			getOaViolation = new OaViolations();
		    	            Long count = 1L; // Start with a count of 1
		    	            getOaViolation.setCount(count);
		    	            getOaViolation.setViolationName(violationType);
		    	            
		    	            // Save the new violation to the database (you will need to handle saving logic)
		    	            oaViolationsRepository.save(getOaViolation);  // Assuming you have a save method in service layer
		    	        } else {
		    	            // If the violation exists, increment the count
		    	            Long getCount = getOaViolation.getCount();
		    	            getOaViolation.setCount(getCount + 1);
		    	        }
		        		int oaInt = getNotif.getCrinNotif()+1;
		        		getNotif.setOaNotif(oaInt);
		        		break;
		        	case "EDUC":
		        		if (getEducViolation == null) {
		        			getEducViolation = new EducViolations();
		    	            Long count = 1L; // Start with a count of 1
		    	            getEducViolation.setCount(count);
		    	            getEducViolation.setViolationName(violationType);
		    	            
		    	            // Save the new violation to the database (you will need to handle saving logic)
		    	            educViolationsRepository.save(getEducViolation);  // Assuming you have a save method in service layer
		    	        } else {
		    	            // If the violation exists, increment the count
		    	            Long getCount = getEducViolation.getCount();
		    	            getEducViolation.setCount(getCount + 1);
		    	        }
		        		int educInt = getNotif.getEducNotif()+1;
		        		getNotif.setEducNotif(educInt);
		        		break;
		        	
		        }
		        getStudent.setViolationsCount(getStudent.getViolationsCount()+1);
		        
		        getNotif.setGuideNotif(getNotif.getGuideNotif()+1);
		        getNotif.setDiscNotif(getNotif.getDiscNotif()+1);
		        studentsService.updateStudents(getStudent.getId(), getStudent);
		        notificationService.updateNotif(getNotif.getId(), getNotif);

	        
//	        session.setAttribute("dept", getStudent.getProgramCode());
//	        session.setAttribute(, violation)
	        session.setAttribute("notif", getStudent.getProgramCode());
	        violation.setStrike(strike);
	        violation.setViolationType(violationType);
	        violation.setStatus(0);
	        violation.setAcademicYear(getAcadByStatus.getYearFrom() + "-" + getAcadByStatus.getYearTo() + " " + getAcadByStatus.getSemester() + " SEM");

	        DateTime getDateTime = new DateTime();
	        violation.setViolationDate(getDateTime.getTimeDate());
	        System.out.println("ok ok ok: "+getDateTime.getTimeDate());
	        // Create the new violation record
	        violationService.createViolation(getStudent, violation);
	       /* if (getStudent.getProgramCode().equals("IS")) {
	        	session.setAttribute("isNotif", )
	        }*/
	        return "redirect:/studentsViolation/studentEntry";
	    }

	@PostMapping("/addAcadYear")
	public String addAcadYear (@ModelAttribute AcademicYear academicYear, HttpSession session) {
		AcademicYear createAcademicYear = new AcademicYear();
		createAcademicYear.setSemester(academicYear.getSemester());
		createAcademicYear.setYearFrom(academicYear.getYearFrom());
		createAcademicYear.setYearTo(academicYear.getYearTo());
		acadYearRepository.save(createAcademicYear);
		return "redirect:/studentsViolation/academicYear";
		
	}
	@PostMapping("/selectAcademicYear")
	@ResponseBody
	public ResponseEntity<?> selectAcademicYear(@RequestParam("id") Long id) {
	    try {
	        // Unselect all academic years
	        AcademicYear getSelectedAcadYear = acadYearRepository.findByStatus(1);
	        System.out.println(getSelectedAcadYear.getSemester()+getSelectedAcadYear.getSemester());
	        if (getSelectedAcadYear != null) {
	            getSelectedAcadYear.setStatus(0);
	            acadService.updateAcad(getSelectedAcadYear.getId(), getSelectedAcadYear);
	        }

	        // Select the specific academic year
	        AcademicYear selectedYear = acadYearRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Academic year not found"));
	        selectedYear.setStatus(1);
	        acadService.updateAcad(id, selectedYear);

	        return ResponseEntity.ok("Academic year selected successfully!");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error selecting academic year: " + e.getMessage());
	    }
	}
	@PostMapping("/approve")
	public String approveMethod(@RequestParam("id") Long id, @RequestParam("userId") Long userId, Model model, HttpSession session ) {
		ViolationRecords getRecord = violationService.getViolationById(id);
		UserEntity getUserById = adminService.getAdminById(userId);
		if (getRecord == null || getUserById == null) {
			System.out.println("record null or user null");
			return "redirect:/studentsViolation/violators?errorMessage=error on approving";
		}
		switch(getUserById.getUserType()) {
			case "departmenthead":
				getRecord.setDeptHeadApproval(1);
				
				break;
			case "disciplinaryofficer":
				getRecord.setDisciplinaryOfficerApproval(1);
				break;
			case "guidancecounselor":
				getRecord.setGuidanceOfficerApproval(1);
				break;
		}
		int compliantStatus = getRecord.getDeptHeadApproval()+getRecord.getDisciplinaryOfficerApproval()+getRecord.getDeptHeadApproval();
		if (compliantStatus == 3) {
			getRecord.setStatus(1);
		}
		
		violationService.updateViolations(getRecord.getId(), getRecord);
		return "redirect:/studentsViolation/violators";
	}
	@GetMapping("/getNotification")
    public ResponseEntity<Notification> getData(HttpSession session) {
        // Assuming 'notificationsRepository' returns a 'Notifications' object
		Notification getNotif = notificationRepository.findFirstRow();
        
        // Log for debugging purposes
        System.out.println("Fetched notification: " + getNotif);
        
        // Return the 'Notifications' object as JSON
        return ResponseEntity.ok(getNotif);
    }
	@PostMapping("/getApprovedOfficers")
	public ResponseEntity<?> getApprovedOfficers(@RequestBody Map<String, Object> requestData) {
	    try {
	        Long id = Long.valueOf(requestData.get("id").toString());
	        ViolationRecords violationRecord = violationService.getViolationById(id);

	        if (violationRecord != null) {
	            ViolationRecordDTO dto = new ViolationRecordDTO();
	            dto.setId(violationRecord.getId());
	            dto.setUserCode(violationRecord.getUserCode());
	            dto.setViolationType(violationRecord.getViolationType());
	            dto.setViolationDate(violationRecord.getViolationDate());
	            dto.setResolutionDate(violationRecord.getResolutionDate());
	            dto.setDisciplinaryAction(violationRecord.getDisciplinaryAction());
	            dto.setDeptHeadApproval(violationRecord.getDeptHeadApproval());
	            dto.setDisciplinaryOfficerApproval(violationRecord.getDisciplinaryOfficerApproval());
	            dto.setGuidanceOfficerApproval(violationRecord.getGuidanceOfficerApproval());

	            // Return the DTO
	            return ResponseEntity.ok(dto);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                                 .body(Collections.singletonMap("error", "Violation record not found."));
	        }
	    } catch (Exception e) {
	        System.err.println("Error in getApprovedOfficers: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(Collections.singletonMap("error", "An error occurred while processing your request."));
	    }
	}




}
