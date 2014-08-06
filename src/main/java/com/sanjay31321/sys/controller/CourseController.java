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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sanjay31321.sys.model.Course;
import com.sanjay31321.sys.model.Course_Subject;
import com.sanjay31321.sys.model.Subject;
import com.sanjay31321.sys.service.CourseService;
import com.sanjay31321.sys.service.CourseSubjectService;
import com.sanjay31321.sys.service.SubjectService;

@Controller
public class CourseController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private CourseService courseService;
	
	@Autowired private SubjectService subjectService;
	@Autowired private CourseSubjectService  courseSubjectService;
	
	@RequestMapping(value = "/coursemanager", method = RequestMethod.GET)
	public String getcoursemanager1(@ModelAttribute("course") Course course, Locale locale, Map<String, Object> map, HttpServletRequest req) {
		logger.info("Welcome to Course Manager page ! The client locale is {}.", locale);
		map.put("courseList", courseService.courseList());
		return "coursemanager";
	}
	
	@RequestMapping(value="/coursemanager", method=RequestMethod.POST)
	public String postcoursemanager(@Valid @ModelAttribute("course") Course course, BindingResult result, HttpServletRequest req, Model model, Map<String, Object> map){
			
		if(!course.getName().isEmpty()) {
			if(!courseService.courseExist(course.getName())) {
				courseService.addCourse(course);
				logger.info("Course registered successfully");
				model.addAttribute("Msg","Course registered successfully.");
			} else {
				logger.error("Course already exists ");
				model.addAttribute("errorMsg","Course already exists ");
			}
		} else {
			logger.error("Course Name must not empty ");
			model.addAttribute("errorMsg","Course Name must not empty");
		}
		map.put("courseList", courseService.courseList());
		return "coursemanager";
	}
	
	@RequestMapping(value = "/editcourse", method = RequestMethod.GET)
	public String geteditcourse(@ModelAttribute("course")Course course, @RequestParam("id")int id, BindingResult result, Locale locale, Map<String, Object> map) {
		logger.info("Welcome to Edit Course page ! The client locale is {}.", locale);		
		map.put("course", courseService.getCourse(id));
		return "editcourse";
	}
	
	@RequestMapping(value = "/deletecourse", method = RequestMethod.GET)
	public String getdeletecourse(@RequestParam("id")int id,   Locale locale, Map<String, Object> map, final RedirectAttributes redirectAttributes) {
		logger.info("Welcome to Delete Course page ! The client locale is {}.", locale);		
		
		
		if(courseService.courseExistsById(id)) {
			
			Course course = courseService.getCourse(id);
			courseService.deleteCourse(course.getId());
			logger.info("Course deleted.");
			redirectAttributes.addFlashAttribute("Msg","Course deleted");
			return "redirect:coursemanager.html";
		} else {
			logger.info("Course does not exists");
			redirectAttributes.addFlashAttribute("errorMsg","Course does not exists");
			map.put("course", courseService.getCourse(id));
			return "coursemanager";
		}
		
	}

	@RequestMapping(value = "/editcourse", method = RequestMethod.POST)
	public String posteditcourse(@Valid @ModelAttribute("course") Course course, BindingResult result, Locale locale, final RedirectAttributes redirectAttributes, HttpServletRequest req, Map<String, Object> map) {
		logger.info("Welcome to Edit Course page ! The client locale is {}.", locale);
		if(!course.getName().isEmpty()) {
			if(courseService.courseExists(course.getName())==null) {
				courseService.editCourse(course);
				logger.debug("Course edited successfully");
				redirectAttributes.addFlashAttribute("Msg","* Course edited successfully.");
				return "redirect:coursemanager.html";
			} else {
				logger.error("Course already exists");
				redirectAttributes.addFlashAttribute("errorMsg","* Course already exists");
				return "redirect:editcourse.html?id="+course.getId();
			}
		} else {
			return "redirect:editcourse.html?id="+course.getId();
		}
	}
	
	@RequestMapping(value = "/assignsubjectstocourse", method = RequestMethod.GET)
	public String getassignsubjectstocourse(@ModelAttribute("subject") Subject subject, Locale locale, BindingResult result, @RequestParam("id") Integer id, HttpServletRequest req, Map<String, Object> map) {
		logger.info("Welcome to Assign Subjects to Course page ! GET METHOD : The client locale is {}.", locale);		
		map.put("course", courseService.getCourse(id));
		map.put("couseSubjectList", courseSubjectService.getCourseSubjectByCourseId(id));
		map.put("subjectList", subjectService.subjectList());
		return "assignsubjectstocourse";
	}
	
	@RequestMapping(value = "/assignsubjectstocourse", method = RequestMethod.POST)
	public String postassignsubjectstocourse(@Valid @ModelAttribute("subject") Subject subject, BindingResult result, Locale locale, HttpServletRequest req, Map<String, Object> map) {
		
		String action = req.getParameter("action");
		int course_id = Integer.parseInt(req.getParameter("course_id"));
		
		Course_Subject course_subject = new Course_Subject();
		Course course = new Course();
		
		if(action.matches("add")) {

			String[] subjects = req.getParameterValues("subject1");
			
			course = courseService.getCourse(course_id);
	
			for(int i=0;i<subjects.length;i++) {
				if(!courseSubjectService.subjectExistsInCourse(course_id, Integer.parseInt(subjects[i]))) {
					subject = subjectService.getSubject(Integer.parseInt(subjects[i]));
					course_subject.setCourse(course);
					course_subject.setSubject(subject);
					courseSubjectService.addCourseSubject(course_subject);
					logger.info("Subject : "+subject.getName()+" assigned to course : "+course.getName()+" added.");
				} else {
					logger.error("1: subject : "+subjects[i]+" already exists in course : "+course_id);
				}
			}
		} else if (action.matches("remove")) {
			
			String[] subjects = req.getParameterValues("subject2");
			
			course = courseService.getCourse(course_id);
	
			for(int i=0;i<subjects.length;i++) {
				if(courseSubjectService.subjectExistsInCourse(course_id, Integer.parseInt(subjects[i]))) {
					subject = subjectService.getSubject(Integer.parseInt(subjects[i]));
					course_subject = courseSubjectService.subjectExistsInCourseID(course_id, Integer.parseInt(subjects[i]));
					courseSubjectService.deleteCourseSubject(course_subject.getId());
					logger.info("Subject : "+subject.getName()+" deleted from course : "+course.getName());
				} else {
					logger.error("2: subject : "+subjects[i]+" does not exists in course : "+course_id);
				}
			}
		}
		
		return "redirect:assignsubjectstocourse.html?id="+course_id;
	}
	
}