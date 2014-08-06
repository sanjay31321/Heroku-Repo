package com.sanjay31321.sys.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sanjay31321.sys.model.User;
import com.sanjay31321.sys.service.UserService;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "redirect:login.html";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@ModelAttribute("user") User user, Locale locale) {
		logger.info("Welcome to Login page ! The client locale is {}.", locale);		
		return "login";
	}
	
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(@ModelAttribute("user") User user, Locale locale) {
		logger.info("Welcome to Admin's Dashboard page ! The client locale is {}.", locale);		
		return "admin";
	}
	
	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public String student(@ModelAttribute("user") User user, Locale locale) {
		logger.info("Welcome to Student's Dashboard Page ! The client locale is {}.", locale);		
		return "student";
	}
	
	@RequestMapping(value = "/teacher", method = RequestMethod.GET)
	public String teacher(@ModelAttribute("user") User user, Locale locale) {
		logger.info("Welcome to Teacher's Dashboard page ! The client locale is {}.", locale);		
		return "teacher";
	}
	
	@RequestMapping(value = "/notactive", method = RequestMethod.GET)
	public String notactive(@ModelAttribute("user") User user, Locale locale) {
		logger.info("Welcome to Not Active page ! The client locale is {}.", locale);		
		return "notactive";
	}
	
	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String error404( Locale locale) {
		return "404";
	}
	
	@RequestMapping(value = "/500", method = RequestMethod.GET)
	public String error500(Locale locale) {
		return "500";
	}
	
	@RequestMapping(value = "/assigncoursetostudent", method = RequestMethod.GET)
	public String assigncoursetostudent(@ModelAttribute("user") User user, Locale locale) {
		logger.info("Welcome to Assign Course to Student page ! The client locale is {}.", locale);		
		return "assigncoursetostudent";
	}
	
	@RequestMapping(value = "/createquestionset", method = RequestMethod.GET)
	public String createquestionset(@ModelAttribute("user") User user, Locale locale) {
		logger.info("Welcome to Create Question Set page ! The client locale is {}.", locale);		
		return "createquestionset";
	}
	@RequestMapping(value = "/createquestion", method = RequestMethod.GET)
	public String createquestion(@ModelAttribute("user") User user, Locale locale) {
		logger.info("Welcome to Create Question page ! The client locale is {}.", locale);		
		return "createquestion";
	}
	
	@RequestMapping(value = "/signupsuccess", method = RequestMethod.GET)
	public String signupsuccess(@ModelAttribute("user") User user, Locale locale) {
		logger.info("Registered Successfully ! Waiting for Approval.  The client locale is {}.", locale);		
		return "signupsuccess";
	}
}
