package ca.sheridancollege.mccries.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.mccries.beans.Course;
import ca.sheridancollege.mccries.beans.Enrollment;
import ca.sheridancollege.mccries.beans.Student;
import ca.sheridancollege.mccries.beans.User;
import ca.sheridancollege.mccries.database.DatabaseAccess;
import ca.sheridancollege.mccries.security.UserDetailsServiceImpl;

/* Name: Sarah McCrie 991405606
* Assignment: Assignment #4
* Date: December 07, 2023
* Program: A4_mccries
*/

public class CourseController {

	@Autowired
	@Lazy
	PasswordEncoder passwordEncoder;

	@Autowired
	@Lazy
	private DatabaseAccess da;

	@Autowired
	@Lazy
	UserDetailsServiceImpl detailService;
	
	@GetMapping("/register")
	public String getRegister() {
		return "register";
	}

	@PostMapping("/register")
	public String postRegister(@RequestParam String username, @RequestParam String password) {
		da.addUser(username, password);
		Long userId = da.findUserAccount(username).getUserId();
		da.addRole(userId, Long.valueOf(1));
		return "redirect:/";
	}

	// index (not logged in/unsecure)
	@GetMapping("/")
	public String index() {
		return "index";
	}

	// secure index
	@GetMapping("/secure")
	public String secureIndex(Model model, Authentication authentication) {
		String email = authentication.getName();
		List<String> roleList = new ArrayList<String>();
		for (GrantedAuthority ga : authentication.getAuthorities()) {
			roleList.add(ga.getAuthority());
		}
		User authenticatedUser = da.findUserAccount(email);
		model.addAttribute("email", email);
		model.addAttribute("roleList", roleList);
		model.addAttribute("userId", authenticatedUser.getUserId());
		return "/secure/index";
	}

	// login
	@GetMapping("/login")
	public String login() {
		System.out.println("it happened");
		return "login";
	}
	
	@GetMapping("/test")
	public String test() {
		System.out.println("it happened");
		return "test";
	}

	// permission denied
	@GetMapping("/permission-denied")
	public String permissionDenied() {
		return "/error/permission-denied";
	}

	// password
	@GetMapping("/pass")
	public String pass() {
		String password = passwordEncoder.encode("1234").toString();
		System.out.println("The encrypted password is " + password);
		boolean m = passwordEncoder.matches("1234", password);
		System.out.println("The password is " + m);
		boolean n = passwordEncoder.matches("124", password);
		System.out.println("The password is " + n);
		return "login";
	}

	// secure pages
	@GetMapping("/secure/courseReg")
	public String courseReg(Model model, Authentication authentication) {
		List<Course> courseListPerTerm = da.getAllCourses();
		model.addAttribute("courseList", courseListPerTerm);
		return "secure/courseReg";
	}
	
		
		
		
		
		
		
		
//		String email = authentication.getName();
//		User thisUser = da.findUserAccount(email);
//		if (thisUser != null) {
//			Student thisStudent = da.getStudentByUserId(thisUser.getUserId());
//			if (thisStudent != null) {
//				List<Course> courseListPerTerm = da.getAllCourses(thisStudent.getStudentId());
//				model.addAttribute("courseList", courseListPerTerm);
//			} else {
//				model.addAttribute("message", "No course details found.");
//			}
//		} else {
//			model.addAttribute("message", "User not found.");
//		}
		
		
//		if (thisStudent == null) {
//			model.addAttribute("student", new Student());
//		} else {
//			model.addAttribute("student", thisStudent);
//		}
//		
//		List<Course> courseListPerTerm = da.getCourseListByTerm();
//		model.addAttribute("courseListPerTerm", courseListPerTerm);
		
	
	
	
	
	// adminsecure pages
	@GetMapping("/adminSecure/transcript")
	public String transcript() {
		return "secure/transcript";
	}
	
	@GetMapping("/adminSecure/grading")
	public String grading() {
		return "adminSecure/grading";
	}

	
	
}
