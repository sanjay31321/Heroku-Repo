package com.sanjay31321.sys.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sanjay31321.sys.model.Subject;
import com.sanjay31321.sys.model.Teacher_Subject;
import com.sanjay31321.sys.model.User;
import com.sanjay31321.sys.service.SubjectService;
import com.sanjay31321.sys.service.TeacherSubjectService;
import com.sanjay31321.sys.service.UserService;

@Controller
public class SubjectController {
	private static final Logger logger = LoggerFactory.getLogger(SubjectController.class);
	
	@Autowired private SubjectService subjectService;
	@Autowired private UserService userService;
	@Autowired private TeacherSubjectService teacherSubjectService;
	
	@RequestMapping(value = "/subjectmanager", method = RequestMethod.GET)
	public String getsubjectmanager( Subject subject, Locale locale, Map<String, Object> map) {
		logger.info("Welcome to Subject Manager page ! GET Method - The client locale is {}.", locale);		
		map.put("subjectList", subjectService.subjectList());
		return "subjectmanager";
	}
	
	@RequestMapping(value="/subjectmanager", method=RequestMethod.POST)
	public String postsubjectmanager(@Valid  Subject subject, BindingResult result, Locale locale, final RedirectAttributes redirectAttributes, HttpServletRequest req, Map<String, Object> map){
		logger.info("Welcome to Subject Manager page ! Post Method - The client locale is {}.", locale);	
		if(!subject.getName().isEmpty()) {
			Subject newsubject = subjectService.subjectExists(subject.getName());			
			if(newsubject==null) {
				subjectService.addSubject(subject);
				logger.info("Subject registered successfully : " +subject.getName());
				redirectAttributes.addFlashAttribute("Msg","Subject registered successfully");
			} else {
				logger.error("Subject already exists : " + subject.getName());
				redirectAttributes.addFlashAttribute("errorMsg", "Subject already exists.");
			}
		} else {
			logger.error("Subject Name must not empty.");
			redirectAttributes.addFlashAttribute("errorMsg","Subject Name must not empty.");
		}
		return "redirect:subjectmanager.html";
	}
	
	@RequestMapping(value = "/editsubject", method = RequestMethod.GET)
	public String geteditsubject(@ModelAttribute("subject") @RequestParam("id")int id, Subject subject, Locale locale, Map<String, Object> map) {
		logger.info("Welcome to Edit Subject page ! The client locale is {}.", locale);		
		map.put("subject", subjectService.getSubject(id));
		return "editsubject";
	}


	@RequestMapping(value = "/editsubject", method = RequestMethod.POST)
	public String posteditsubject(@Valid @ModelAttribute("subject") Subject subject, BindingResult result, Locale locale, final RedirectAttributes redirectAttributes, HttpServletRequest req, Map<String, Object> map) {
		logger.info("Welcome to Edit Subject page ! The client locale is {}.", locale);
		if(!subject.getName().isEmpty()) {
			if(subjectService.subjectExists(subject.getName())==null) {
				subjectService.editSubject(subject);
				logger.debug("Subject edit successfully.");
				redirectAttributes.addFlashAttribute("errorMsg","Subject edit successfully.");
				return "redirect:subjectmanager.html";
			} else {
				logger.error("Subject already exists");
				redirectAttributes.addFlashAttribute("errorMsg","Subject already exists");
				return "redirect:editsubject.html?id="+subject.getId();
			}
		} else {
			redirectAttributes.addFlashAttribute("errorMsg","Subject field must not empty");
			return "redirect:editsubject.html?id="+subject.getId();
		}
	}
	
	@RequestMapping(value = "/deletesubject", method = RequestMethod.GET)
	public String getdeletesubject(@RequestParam("id")int id,   Locale locale, final RedirectAttributes redirectAttributes) {
		logger.info("Welcome to Delete Subject page ! The client locale is {}.", locale);		
		
		Subject subject = subjectService.getSubject(id);
		if(subject != null) {
			subjectService.deleteSubject(subject.getId());
			logger.info("Subject deleted : " + subject.getName());
			redirectAttributes.addFlashAttribute("Msg","Subject deleted.");
			return "redirect:subjectmanager.html";
		} else {
			logger.info("Subject does not exists : ");
			redirectAttributes.addFlashAttribute("errorMsg","Subject does not exists.");
			return "redirect:subjectmanager.html";
		}
	}
	
	@RequestMapping(value = "/assignsubjectstoteacher", method = RequestMethod.GET)
	public String getassignsubjectstoteacher(@ModelAttribute("subject") Subject subject, Locale locale, BindingResult result, @RequestParam("id") Integer id, HttpServletRequest req, Map<String, Object> map) {
		logger.info("Welcome to Assign Subjects to Teacher page ! GET METHOD : The client locale is {}.", locale);		
		
		map.put("teacher", userService.getUser(id));
		map.put("teacherSubjectList", teacherSubjectService.getTeacherSubjectByTeacherId(id));
		map.put("subjectList", subjectService.subjectList());
		return "assignsubjectstoteacher";
	}
	
	@RequestMapping(value = "/assignsubjectstoteacher", method = RequestMethod.POST)
	public String postassignsubjectstocourse(@Valid @ModelAttribute("subject") Subject subject, BindingResult result, Locale locale,   HttpServletRequest req, Map<String, Object> map) {
		
		String action = req.getParameter("action");
		int teacher_id = Integer.parseInt(req.getParameter("teacher_id"));
		
		Teacher_Subject teacher_subject = new Teacher_Subject();
		User user = new User();
		
		if(action.matches("add")) {

			String[] subjects = req.getParameterValues("subject1");
			
			user = userService.getUser(teacher_id);
	
			for(int i=0;i<subjects.length;i++) {
				if(!teacherSubjectService.subjectExistsWithTeacherID(teacher_id, Integer.parseInt(subjects[i]))) {
					subject = subjectService.getSubject(Integer.parseInt(subjects[i]));
					teacher_subject.setUser(user);
					teacher_subject.setSubject(subject);
					teacherSubjectService.addTeacherSubject(teacher_subject);
					logger.info("Subject : "+subject.getName()+" assigned to teacher : "+user.getName()+" added.");
				} else {
					logger.error("1: subject : "+subjects[i]+" already exists with teacher : "+teacher_id);
				}
			}
		} else if (action.matches("remove")) {
			
			String[] subjects = req.getParameterValues("subject2");
			
			user = userService.getUser(teacher_id);
	
			for(int i=0;i<subjects.length;i++) {
				if(teacherSubjectService.subjectExistsWithTeacherID(teacher_id, Integer.parseInt(subjects[i]))) {
					subject = subjectService.getSubject(Integer.parseInt(subjects[i]));
					teacher_subject = teacherSubjectService.subjectExistsWithTeacher(teacher_id, Integer.parseInt(subjects[i]));
					teacherSubjectService.deleteTeacherSubject(teacher_subject.getId());
					logger.info("Subject : "+subject.getName()+" deleted from teacher : "+user.getName());
				} else {
					logger.error("2: subject : "+subjects[i]+" does not  exists with teacher : "+teacher_id);
				}
			}
		}
		
		return "redirect:assignsubjectstoteacher.html?id="+teacher_id;
	}
}