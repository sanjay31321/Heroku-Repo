package com.sanjay31321.sys.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sanjay31321.sys.model.Course;
import com.sanjay31321.sys.model.Student_Course;
import com.sanjay31321.sys.model.User;
import com.sanjay31321.sys.service.CourseService;
import com.sanjay31321.sys.service.StudentCourseService;
import com.sanjay31321.sys.service.UserService;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired private UserService userService;
	@Autowired private StudentCourseService studentCourseService;
	@Autowired private CourseService courseService;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@Valid User user, BindingResult result, HttpServletRequest req, Model model){

		logger.info("User is trying to log in with: Email: " + user.getEmail()
				+ " with password: " + user.getPassword());
		User loggedInUser = userService.findUserForLogin(user.getEmail(),user.getPassword());
		
		req.getSession().setMaxInactiveInterval(1000*1000);
		if(loggedInUser != null) {
			req.getSession().setAttribute("loggedInUserEMAIL", loggedInUser.getEmail());
			if(loggedInUser.isActive()) {
				switch(loggedInUser.getUser_role().getId()){
					case 1:
						req.getSession().setAttribute("loggedInUserROLE", loggedInUser.getUser_role().getName());
						req.getSession().setAttribute("loggedInUserNAME", loggedInUser.getName());
						req.getSession().setAttribute("role","authenticated");
						req.getSession().setAttribute("loggedInUserHOMELINK","feedbackmanager.html");
						req.getSession().setAttribute("loggedInUserHOME","app.breadcrumb.adminhome");
						return "redirect:feedbackmanager.html";
					case 2: 
						req.getSession().setAttribute("loggedInUserROLE", loggedInUser.getUser_role().getName());
						req.getSession().setAttribute("loggedInUserID", loggedInUser.getId());
						req.getSession().setAttribute("loggedInUserNAME", loggedInUser.getName());
						req.getSession().setAttribute("role","authenticated");
						req.getSession().setAttribute("loggedInUserHOMELINK","feedbackmanager.html");
						req.getSession().setAttribute("loggedInUserHOME","app.breadcrumb.teacherhome");
						return "redirect:feedbackmanager.html";
					case 3:
						req.getSession().setAttribute("loggedInUserROLE", loggedInUser.getUser_role().getName());
						req.getSession().setAttribute("loggedInUserNAME", loggedInUser.getName());
						req.getSession().setAttribute("loggedInUserID", loggedInUser.getId());
						Student_Course student_course = studentCourseService.getStudentCourseByUserID(loggedInUser.getId());
						Course course = userService.getCourse(student_course.getCourse().getId());
						req.getSession().setAttribute("loggedInUserCOURSE", course.getName());
						req.getSession().setAttribute("loggedInUserHOMELINK","studentfeedbacks.html?email="+loggedInUser.getEmail());
						req.getSession().setAttribute("loggedInUserHOME","app.breadcrumb.studenthome");
						return "redirect:studentfeedbacks.html?email="+loggedInUser.getEmail();
				}
			} else {
				logger.error("User is not active" + loggedInUser.getEmail());
				return "notactive";
			}
			return "admin";
		} else {
			logger.error("Login failed ! User Does not Exists !");
			model.addAttribute("errorMsg", "Login failed !");
			return "login";
		}
		
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String getsignup(@ModelAttribute("user") User user, Locale locale, Map<String, Object> map) {
		logger.info("Welcome to Sign Up page ! GET Methods The client locale is {}.", locale);	
		map.put("courseList", userService.getCourseList());
		return "signup";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String postsignup(@Valid User user, BindingResult result, HttpServletRequest req, Model model, Map<String, Object> map){
		
		if(user.getEmail().isEmpty() && user.getPassword().isEmpty() && user.getName().isEmpty() &&
				user.getConfirm_password().matches(user.getPassword()) && user.getSecurity_answer().isEmpty() && 
				user.getSecurity_question().isEmpty() && user.getSecurity_question().isEmpty() && user.getUser_role().getId() == 0 ) {
			model.addAttribute("errorMsg", "Please complete the form");
			map.put("courseList", userService.getCourseList());
			return "signup";
		}
		
		if(user.getUser_role().getId() == 3 && user.getCourse_id() == 0) {
			model.addAttribute("errorMsg", "Please select your course.");
			map.put("courseList", userService.getCourseList());
			return "signup";
		}
		
		if(!userService.userExist(user.getEmail())) {
			logger.info(" " + user.getPassword() +" / "+ user.getConfirm_password()+" "+user.getUser_role().getId());
			if(user.getPassword().equals(user.getConfirm_password())) {
				if(user.getUser_role().getId() == 2) {
					userService.Register(user);
					logger.info("Registration Successful.");
					return "signupsuccess";
				} else if(user.getUser_role().getId() == 3) {
					userService.Register(user);
					Student_Course student_course = new Student_Course();
					student_course.setUser(user);
					student_course.setCourse(userService.getCourse(user.getCourse_id()));
					studentCourseService.addStudentCourse(student_course);
					logger.info("Registration Successful.");
					return "signupsuccess";
				} else {
					logger.error("role does not exists");
					model.addAttribute("errorMsg", "role does not exists");
				}
			} else {
				logger.error("Password must match.");
				model.addAttribute("errorMsg","Password must match.");
			}
			
		} else {
			logger.error("User already exists with this email : " +user.getEmail());
			model.addAttribute("errorMsg", "User already exists with this email : " +user.getEmail());
		}
		return "signup";
	}
	
	@RequestMapping(value = "/usermanager", method = RequestMethod.GET)
	public String getusermanager( User user, Locale locale, Map<String, Object> map) {
		logger.info("Welcome to User Manager page ! GET Method - The client locale is {}.", locale);		
		map.put("userList", userService.getAllUsers());
		return "usermanager";
	}
	
	@RequestMapping(value = "/forgetpassword", method = RequestMethod.GET)
	public String getforgetpassword(@ModelAttribute("user") User user, Locale locale) {
		logger.info("Welcome to Forget Password page ! The client locale is {}.", locale);		
		return "forgetpassword";
	}
	
	@RequestMapping(value="/forgetpassword", method=RequestMethod.POST)
	public String postforgetpassword(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){

		if(user.getEmail().isEmpty()){
			model.addAttribute("errorMsg", "Please complete the form");
			return "forgetpassword"; 
		}
		if(userService.userExist(user.getEmail())) {
			return "redirect:verifysecurityquestion.html?email="+user.getEmail();
		} else {
			logger.error("User doesn't exists");
			model.addAttribute("errorMsg", "User doesn't exists");
			return "forgetpassword";
		}
		
	}
	
	@RequestMapping(value = "/verifysecurityquestion", method = RequestMethod.GET)
	public String getverifysecurityquestion(@ModelAttribute("user") User user, @RequestParam("email") String email, Locale locale, Map<String, Object> map) {
		logger.info("Welcome to Verify Security Question page ! The client locale is {}.", locale);
		user = userService.userExists(email);
		map.put("user", user);
		return "verifysecurityquestion";
	}
	
	@RequestMapping(value="/verifysecurityquestion", method=RequestMethod.POST)
	public String postverifysecurityquestion(@ModelAttribute("user") User user,Model model, @RequestParam("email") String email, HttpServletRequest req, Locale locale, Map<String, Object> map) {
		
		if(user.getSecurity_answer().isEmpty()) {
			user = userService.userExists(email);
			map.put("user", user);
			model.addAttribute("errorMsg", "Please complete the form");
			return "verifysecurityquestion"; 
		}
		
		User user1 = userService.userExists(email);
		if(user.getSecurity_answer().equals(user1.getSecurity_answer())) {
			req.setAttribute("resetSession", "resetThisUser");
			return "redirect:reset.html?email="+user.getEmail();
		} else {
			user = userService.userExists(email);
			map.put("user", user);
			model.addAttribute("errorMsg", "Worng Security Answer.");
			return "verifysecurityquestion";
		}
	}
	
	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public String getresetpassword(@ModelAttribute("user") User user, Locale locale) {
		logger.info("Welcome to Reset Password page ! GET Method : The client locale is {}.", locale);
		return "reset";
	}
	
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public String postresetpassword(@ModelAttribute("user") User user, Locale locale, Map<String, Object> map, HttpServletRequest req, Model model) {
		logger.info("Welcome to Reset Password page ! POST Method : The client locale is {}.", locale);
		
		if(user.getPassword().isEmpty()) {
			model.addAttribute("errorMsg", "Please complete the form.");
			return "reset";
		}
		if(user.getConfirm_password().isEmpty()) {
			model.addAttribute("errorMsg", "Please complete the form.");
			return "reset";
		}
		
		System.out.println(user.getPassword() +" : "+ user.getConfirm_password());
		
		if(!user.getPassword().matches(user.getConfirm_password())) {
			model.addAttribute("errorMsg", "Passwords must match.");
			return "reset";
		}
		
		User user1 = userService.userExists(user.getEmail());
		if(userService.userExist(user.getEmail())) {
			user1.setPassword(user.getPassword());
			userService.updateUser(user1);
			logger.info("Password changed successfully");
			req.setAttribute("resetSession", null);
			return "redirect:resetsuccess.html";
		} else {
			logger.error("User doesn't exists");
			model.addAttribute("errorMsg","User does not exists.");
			return "reset";
		}
		
	}
	
	@RequestMapping(value = "/resetsuccess", method = RequestMethod.GET)
	public String resetsuccess(@ModelAttribute("user") User user, Locale locale) {
		logger.info("Welcome to Reset Password Success page ! The client locale is {}.", locale);
		return "resetsuccess";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Locale locale, HttpServletRequest req) {
		logger.info("Welcome to Logout page ! GET Method - The client locale is {}.", locale);		
		req.getSession().setAttribute("loggedInUserROLE", null);
		req.getSession().setAttribute("loggedInUserID", null);
		req.getSession().setAttribute("loggedInUserEMAIL", null);
		req.getSession().setAttribute("loggedInUserNAME", null);
		req.getSession().setAttribute("loggedInUserCOURSE", null);
		req.getSession().setAttribute("loggedInUserHOME", null);
		req.getSession().setAttribute("loggedInUserHOMELINK", null);
		req.getSession().setAttribute("role", null);
		return "redirect:login.html";
	}
	
	@RequestMapping(value = "/teachermanager", method = RequestMethod.GET)
	public String getteachermanager(@ModelAttribute("user") User user, Locale locale, Map<String, Object> map) {
		logger.info("Welcome to Teacher Manager page ! GET Method : The client locale is {}.", locale);
		map.put("teacherList", userService.getAllUsers());
		return "teachermanager";
	}
	
	@RequestMapping(value = "/studentmanager", method = RequestMethod.GET)
	public String getstudentmanager(@ModelAttribute("user") User user, Locale locale, Map<String, Object> map) {
		logger.info("Welcome to Student Manager page ! GET Method : The client locale is {}.", locale);	
		map.put("courseList", courseService.courseList());
		map.put("studentCourseList", studentCourseService.studentCourseList());
		map.put("studentList", userService.getAllUsers());
		return "studentmanager";
	}
}
