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

import com.sanjay31321.sys.model.Question_Set;
import com.sanjay31321.sys.service.QuestionService;
import com.sanjay31321.sys.service.QuestionSetService;

@Controller
public class QuestionSetController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired private QuestionSetService questionSetService;
	@Autowired private QuestionService questionService;
	
	@RequestMapping(value = "/questionsetmanager", method = RequestMethod.GET)
	public String getquestionsetmanager(@ModelAttribute("question_set") Question_Set question_set, Locale locale, Map<String, Object> map) {
		logger.info("Welcome to Question Set Manager page ! GET Method : The client locale is {}.", locale);		
		map.put("questionsetList", questionSetService.questionSetList());
		return "questionsetmanager";
	}
	
	@RequestMapping(value="/questionsetmanager", method=RequestMethod.POST)
	public String postquestionsetmanager(@Valid @ModelAttribute("question_set") Question_Set question_set, BindingResult result, HttpServletRequest req, final RedirectAttributes redirectAttributes, Map<String, Object> map){
			
		if(!question_set.getName().isEmpty()) {
			if(!questionSetService.questionSetExistsByName(question_set.getName())) {
				questionSetService.addQuestionSet(question_set);
				logger.info("Question Set registered successfully : " +question_set.getName());
				redirectAttributes.addFlashAttribute("Msg","Question Set registered successfully.");
			} else {
				logger.error("Question Set already exists.");
				redirectAttributes.addFlashAttribute("errorMsg","Question Set already exists");
			}
		} else {
			logger.error("Question Set Name must not be empty.");
			redirectAttributes.addFlashAttribute("errorMsg","Question Set Name must not  be empty.");
		}
		return "redirect:questionsetmanager.html";
	}
	
	@RequestMapping(value = "/editquestionset", method = RequestMethod.GET)
	public String geteditquestionset(@ModelAttribute("question_set") Question_Set question_set, @RequestParam("id")int id, BindingResult result, Locale locale, Map<String, Object> map) {
		logger.info("Welcome to Edit Question Set  page ! GET Method :The client locale is {}.", locale);		
		map.put("question_set", questionSetService.getQuestionSet(id));
		return "editquestionset";
	}


	@RequestMapping(value = "/editquestionset", method = RequestMethod.POST)
	public String posteditquestionset(@Valid @ModelAttribute("question_set") Question_Set question_set, BindingResult result, final RedirectAttributes redirectAttributes, Locale locale, HttpServletRequest req, Map<String, Object> map) {
		logger.info("Welcome to Edit Question Set page ! The client locale is {}.", locale);
		if(!question_set.getName().isEmpty()) {
			if(!questionSetService.questionSetExistsByName(question_set.getName())) {
				questionSetService.editQuestionSet(question_set);
				logger.debug("Question set edit success.");
				redirectAttributes.addFlashAttribute("Msg","Question set edited successfully.");
				return "redirect:questionsetmanager.html";
			} else {
				logger.error("Question set already exists.");
				redirectAttributes.addFlashAttribute("errorMsg","Question set already exists.");
				return "redirect:editquestionset.html?id="+question_set.getId();
			}
		} else {
			redirectAttributes.addFlashAttribute("errorMsg","Name must not be empty");
			return "redirect:editquestionset.html?id="+question_set.getId();
		}
	}
	
	@RequestMapping(value = "/deletequestionset", method = RequestMethod.GET)
	public String getdeletequestionset(@RequestParam("id")int id,   Locale locale, final RedirectAttributes redirectAttributes) {
		logger.info("Welcome to Delete Question Set page ! The client locale is {}.", locale);		
		
		if(questionSetService.questionSetExistsById(id)) {
			Question_Set question_set = questionSetService.getQuestionSet(id);
			questionSetService.deleteQuestionSet(question_set.getId());
			logger.info("Question Set deleted : "+question_set.getName());
			redirectAttributes.addFlashAttribute("Msg","Question Set deleted.");
			return "redirect:questionsetmanager.html";
		} else {
			logger.info("Question Set does not exists.");
			redirectAttributes.addFlashAttribute("errorMsg","Question Set does not exists.");
			return "redirect:questionsetmanager.html";
		}
	}
}