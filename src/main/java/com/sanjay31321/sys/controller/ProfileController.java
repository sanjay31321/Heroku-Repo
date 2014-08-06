package com.sanjay31321.sys.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sanjay31321.sys.model.Course;
import com.sanjay31321.sys.model.Student_Course;
import com.sanjay31321.sys.model.User;
import com.sanjay31321.sys.model.User_Role;
import com.sanjay31321.sys.preset.data.InsertUserRole;
import com.sanjay31321.sys.service.CourseService;
import com.sanjay31321.sys.service.CourseSubjectService;
import com.sanjay31321.sys.service.StudentCourseService;
import com.sanjay31321.sys.service.SubjectService;
import com.sanjay31321.sys.service.UserRoleService;
import com.sanjay31321.sys.service.UserService;

@Controller
public class ProfileController {
	private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
	
	@Autowired
	private UserService userService;
	@Autowired private UserRoleService userRoleService;
	@Autowired private StudentCourseService studentCourseService;
	@Autowired private CourseSubjectService courseSubjectService;
	@Autowired private SubjectService subjectService;
	@Autowired private CourseService courseService;
	@Autowired private InsertUserRole  insertUserRole;
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(@ModelAttribute("user") User user, Locale locale) {
		logger.info("Welcome to User Profile page ! The client locale is {}.", locale);		
		return "profile";
	}
	
	@RequestMapping(value = "/studentprofile", method = RequestMethod.GET)
	public String studentprofile(@ModelAttribute("user") User user, Locale locale) {
		logger.info("Welcome to Student Profile page ! The client locale is {}.", locale);		
		return "studentprofile";
	}
	
	@RequestMapping(value = "/editprofile", method = RequestMethod.GET)
	public String geteditprofile(@ModelAttribute("user") User user, @RequestParam("email")String email, Locale locale, Map<String, Object> map) {
		logger.info("Welcome to Edit Profile page ! The client locale is {}.", locale);		
		map.put("profile", userService.userExists(email));
		return "editprofile";
	}
	
	@RequestMapping(value = "/editprofile", method = RequestMethod.POST)
	public String posteditprofile(@ModelAttribute("user") User user, @RequestParam("email")String email, HttpServletRequest req, final RedirectAttributes redirectAttributes, Locale locale, Map<String, Object> map) {
		logger.info("Welcome to Edit Profile page !POST Method: The client locale is {}.", locale);		
		User loggedInUser = userService.userExists(email);
		loggedInUser.setName(user.getName());
		loggedInUser.setSecurity_question(user.getSecurity_question());
		loggedInUser.setSecurity_answer(user.getSecurity_answer());
		loggedInUser.setPassword(user.getPassword());
		
		userService.updateUser(loggedInUser);
		logger.info("Profile Updated : "+loggedInUser.getEmail());
		redirectAttributes.addFlashAttribute("Msg","Profile Updated.");
		req.getSession().setAttribute("loggedInUserNAME", user.getName());
		return "redirect:profile.html";
	}
	
	@RequestMapping(value = "/edituser", method = RequestMethod.GET)
	public String getedituser(@ModelAttribute("user") User user, @RequestParam("id")Integer id, Locale locale, Map<String, Object> map) {
		logger.info("Welcome to Edit User page ! The client locale is {}.", locale);		
		map.put("profile", userService.getUser(id));
		map.put("userRoleList", userRoleService.userRoleList());
		map.put("subjectList",subjectService.subjectList());
		map.put("courseList", courseService.courseList());
		map.put("studentCourseList", studentCourseService.studentCourseList());
		map.put("courseSubjectList", courseSubjectService.courseSubjectList());
		return "edituser";
	}
	
	@RequestMapping(value = "/adduser", method = RequestMethod.GET)
	public String getadduser(@ModelAttribute("user") User user,  Locale locale, Map<String, Object> map) {
		logger.info("Welcome to Add User page ! The client locale is {}.", locale);		
		map.put("userRoleList", userRoleService.userRoleList());
		return "adduser";
	}
	
	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public String postadduser(@ModelAttribute("user") User user,  HttpServletRequest req, final RedirectAttributes redirectAttributes, Locale locale, Map<String, Object> map) {
		
		if(user.getEmail().isEmpty() && user.getName().isEmpty() && user.getUser_role().getId() ==0 && user.getPassword().isEmpty()) {
			redirectAttributes.addFlashAttribute("errorMsg","Please complete the form.");
			return "redirect:adduser.html";
		}
		
		User_Role user_role = userRoleService.getUserRole(user.getUser_role().getId());
		
		if(user_role == null) {
			redirectAttributes.addFlashAttribute("errorMsg","Please select user group.");
			return "redirect:adduser.html";
		}
		
		if(!userService.userExist(user.getEmail())) {
			user.setUser_role(user_role);
			user.setActive(true);
			userService.Register(user);
			logger.info("User added : "+ user.getEmail() +" - "+user.getName());
			redirectAttributes.addFlashAttribute("Msg","User added : "+ user.getEmail() +" - "+user.getName());
			return "redirect:usermanager.html";
		} else {
			logger.error("User already exists.");
			redirectAttributes.addFlashAttribute("errorMsg","User already exists.");
			return "adduser";
		}
	}
	
	@RequestMapping(value = "/edituser", method = RequestMethod.POST)
	public String postedituser(@ModelAttribute("user") User user,  HttpServletRequest req, final RedirectAttributes redirectAttributes, Locale locale, Map<String, Object> map) {
		logger.info("Welcome to Edit User  page !POST Method: The client locale is {}.", locale);
		System.out.println(req.getParameter("id")+" - role : "+req.getParameter("role"));
		int id = Integer.parseInt(req.getParameter("id"));
		int role = Integer.parseInt(req.getParameter("role"));
		
		User updateUser = userService.getUser(id);
		User_Role user_role = userRoleService.getUserRole(role);
		
		if(updateUser != null && user_role != null) {
			user.setUser_role(user_role);
			user.setActive(true);
			userService.updateUser(user);
			logger.info("User Updated successfully : "+ user.getEmail());
			redirectAttributes.addFlashAttribute("Msg","User Updated successfully");
			return "redirect:usermanager.html";
		} else {
			logger.error("User does not exists.");
			redirectAttributes.addFlashAttribute("errorMsg","User does not exists.");
			return "redirect:edituser.html?id="+id;
		}
		
	}
	
	@RequestMapping(value = "/setrole", method = RequestMethod.GET)
	public String getsetrole(@ModelAttribute("user") User user, @RequestParam("id")Integer id, Locale locale, Map<String, Object> map) {
		logger.info("Welcome to Set User Role page ! The client locale is {}.", locale);		
		map.put("profile", userService.getUser(id));
		map.put("userRoleList", userRoleService.userRoleList());
		map.put("subjectList",subjectService.subjectList());
		map.put("courseList", courseService.courseList());
		map.put("studentCourseList", studentCourseService.studentCourseList());
		return "setrole";
	}
	
	@RequestMapping(value = "/setrole", method = RequestMethod.POST)
	public String postsetrole(@ModelAttribute("user") User user,  HttpServletRequest req, Locale locale, final RedirectAttributes redirectAttributes, Map<String, Object> map) {
		logger.info("Welcome to Set User Role page ! POST Method: The client locale is {}.", locale);
		int id = Integer.parseInt(req.getParameter("id"));
		
		if(user.getUser_role().getId() == 0 ) {
			redirectAttributes.addFlashAttribute("errorMsg","Please select user group.");
			return "redirect:setrole.html?id="+id;
		}
		
		User updateUser = userService.getUser(id);
		User_Role user_role = userRoleService.getUserRole(user.getUser_role().getId());
		
		if(updateUser != null && user_role != null) {
			updateUser.setUser_role(user_role);
			updateUser.setActive(true);
			userService.updateUser(updateUser);
			logger.info("User Updated successfully : "+updateUser.getEmail());
			redirectAttributes.addFlashAttribute("Msg","User Updated successfully");
			return "redirect:usermanager.html";
		} else {
			logger.error("User does not exists.");
			redirectAttributes.addFlashAttribute("errorMsg","User does not exists.");
			return "redirect:setrole.html?id="+id;
		}
	}

	@RequestMapping(value = "/changecourse", method = RequestMethod.GET)
	public String getchangecourse(@ModelAttribute("course") Course course, @RequestParam("id")Integer id, Locale locale, Map<String, Object> map) {
		logger.info("Welcome to Change Course page ! The client locale is {}.", locale);		
		map.put("profile", userService.getUser(id));
		map.put("subjectList",subjectService.subjectList());
		map.put("courseList", courseService.courseList());
		map.put("studentCourseList", studentCourseService.studentCourseList());
		map.put("courseSubjectList", courseSubjectService.courseSubjectList());
		return "changecourse";
	}
	
	@RequestMapping(value = "/changecourse", method = RequestMethod.POST)
	public String postchangecourse(@ModelAttribute("course") Course course,  HttpServletRequest req, Locale locale, final RedirectAttributes redirectAttributes, Map<String, Object> map) {
		logger.info("Welcome to Change Course page ! POST Method: The client locale is {}.", locale);

		int id = Integer.parseInt(req.getParameter("id"));
		int course_id = Integer.parseInt(req.getParameter("course"));
		
		if(course_id == 0 ) {
			redirectAttributes.addFlashAttribute("errorMsg","Please select user group.");
			return "redirect:changecourse.html?id="+id;
		}
		
		User user = userService.getUser(id);
		Student_Course  student_course = studentCourseService.getStudentCourseByUserID(id);
		Course newCourse = courseService.getCourse(course_id);
		
		if(user != null && newCourse != null && student_course != null) {
			student_course.setUser(user);
			student_course.setCourse(newCourse);
			studentCourseService.editStudentSubject(student_course);
			logger.info("Course Changed successfully.");
			redirectAttributes.addFlashAttribute("Msg","Course Changed successfully.");
			return "redirect:studentmanager.html";
		} else {
			logger.error("User does not exists.");
			redirectAttributes.addFlashAttribute("errorMsg","User does not exists.");
			return "redirect:changecourse.html?id="+id;
		}
	}
	
	@RequestMapping(value = "/editstudentprofile", method = RequestMethod.GET)
	public String editstudentprofile(@ModelAttribute("user") User user, @RequestParam("email")String email, Locale locale, Map<String, Object> map) {
		logger.info("Welcome to Edit Student Profile page ! The client locale is {}.", locale);		
		map.put("profile", userService.userExists(email));
		return "editstudentprofile";
	}
	
	@RequestMapping(value = "/editstudentprofile", method = RequestMethod.POST)
	public String posteditstudentprofile(@ModelAttribute("user") User user, @RequestParam("email")String email, HttpServletRequest req, final RedirectAttributes redirectAttributes,  Locale locale, Map<String, Object> map) {
		logger.info("Welcome to Edit Student Profile page !POST Method: The client locale is {}.", locale);	
		User loggedInUser = userService.userExists(email);
		loggedInUser.setName(user.getName());
		loggedInUser.setSecurity_question(user.getSecurity_question());
		loggedInUser.setSecurity_answer(user.getSecurity_answer());
		loggedInUser.setPassword(user.getPassword());
		
		userService.updateUser(loggedInUser);
		logger.info("User Updated");
		redirectAttributes.addFlashAttribute("Msg","Profile Update.");
		req.getSession().setAttribute("loggedInUserNAME", user.getName());
		return "redirect:studentprofile.html";
	}
	
	@RequestMapping(value = "/deleteuser", method = RequestMethod.GET)
	public String getdeleteuser(@RequestParam("id") Integer id, final RedirectAttributes redirectAttributes, Locale locale) {
		User user = userService.getUser(id);
		if(user != null) {
			userService.deleteUser(user.getId());
			logger.info("User deleted successfully.");
			redirectAttributes.addFlashAttribute("Msg","User deleted successfully.");
			return "redirect:usermanager.html";
		} else {
			logger.error("User doesn't exists.");
			redirectAttributes.addFlashAttribute("errorMsg","User doesn't exists.");
			return "redirect:usermanager.html";
		}
	}
	
	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	public String activate(@RequestParam("id") Integer id, @RequestParam("role") Integer role, @RequestParam("page") String page, final RedirectAttributes redirectAttributes, Locale locale) {
		User user = userService.getUser(id);
		User_Role user_role = userRoleService.getUserRole(role);
		if(user != null) {
			user.setActive(true);
			user.setUser_role(user_role);
			userService.updateUser(user);
			logger.info("User activated successfully", user.getUser_role().getName());
			redirectAttributes.addFlashAttribute("Msg","Account Activated.");
			if(page.matches("user")) {
				return "redirect:usermanager.html";
			} else if(page.matches("teacher")) {
				return "redirect:teachermanager.html";
			} else {
				return "redirect:studentmanager.html";
			}
		} else {
			logger.error("User doesn't exists");
			redirectAttributes.addFlashAttribute("errorMsg","User doesn't exists");
			
			if(page.matches("user")) {
				return "redirect:usermanager.html";
			} else if(page.matches("teacher")) {
				return "redirect:teachermanager.html";
			} else {
				return "redirect:studentmanager.html";
			}
		}
	}
	
	@RequestMapping(value = "/deactivate", method = RequestMethod.GET)
	public String deactivate(@RequestParam("id") Integer id, @RequestParam("role") Integer role, @RequestParam("page") String page, final RedirectAttributes redirectAttributes, Locale locale) {
		User user = userService.getUser(id);
		User_Role user_role = userRoleService.getUserRole(role);
		if(user != null) {
			user.setActive(false);
			user.setUser_role(user_role);
			userService.updateUser(user);
			logger.info("User deactivated successfully",user.getUser_role().getName());
			redirectAttributes.addFlashAttribute("Msg","Account deactivated.");
			if(page.matches("user")) {
				return "redirect:usermanager.html";
			} else if(page.matches("teacher")) {
				return "redirect:teachermanager.html";
			} else {
				return "redirect:studentmanager.html";
			}
		} else {
			logger.error("User doesn't exists.");
			redirectAttributes.addFlashAttribute("errorMsg","User doesn't exists.");
			
			if(page.matches("user")) {
				return "redirect:usermanager.html";
			} else if(page.matches("teacher")) {
				return "redirect:teachermanager.html";
			} else {
				return "redirect:studentmanager.html";
			}
		}
		
	}
}