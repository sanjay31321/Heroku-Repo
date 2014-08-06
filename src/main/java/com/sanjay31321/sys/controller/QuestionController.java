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

import com.sanjay31321.sys.model.Question;
import com.sanjay31321.sys.model.Question_Set;
import com.sanjay31321.sys.service.QuestionService;
import com.sanjay31321.sys.service.QuestionSetService;

@Controller
public class QuestionController {
	private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
	
	@Autowired private QuestionService questionService;
	@Autowired private QuestionSetService questionSetService;
	
	@RequestMapping(value = "/questionmanager", method = RequestMethod.GET)
	public String getquestionmanager(@ModelAttribute("question") Question question, @RequestParam("id") Integer id, Locale locale, Map<String, Object> map) {
		logger.info("Welcome to Question  Manager page ! GET Method : The client locale is {}.", locale);		
		map.put("question_set", questionSetService.getQuestionSet(id));
		map.put("questionList", questionService.questionList());
		return "questionmanager";
	}
	
	@RequestMapping(value="/questionmanager", method=RequestMethod.POST)
	public String postquestionmanager(@Valid @ModelAttribute("question") Question question, @RequestParam("id") Integer id, BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest req, Map<String, Object> map){
			
		if(!question.getQuestion().isEmpty()) {
			question.setQuestion_set(questionSetService.getQuestionSet(id));
			questionService.addQuestion(question);
			logger.info("Question registered successfully : " + question.getQuestion());
			redirectAttributes.addFlashAttribute("Msg","Question registered successfully.");
		} else {
			logger.error("Question Name must not empty.");
			redirectAttributes.addFlashAttribute("errorMsg","Question Name must not empty");
		}
		return "redirect:questionmanager.html?id="+question.getQuestion_set().getId();
	}
	
	
	@RequestMapping(value = "/deletequestion", method = RequestMethod.GET)
	public String getdeletequestion(@RequestParam("id") int id,   Locale locale, final RedirectAttributes redirectAttributes, HttpServletRequest req) {
		logger.info("Welcome to Delete Question Set page ! The client locale is {}.", locale);		
		int questionSetID =Integer.parseInt(req.getParameter("question_set_id"));
		
		if(questionService.questionExistsById(id)) {
			questionService.deleteQuestion(id);
			logger.info("Question deleted.");
			redirectAttributes.addFlashAttribute("Msg","Question deleted.");
			return "redirect:questionmanager.html?id="+questionSetID;
		} else {
			logger.info("Question does not exists");
			redirectAttributes.addFlashAttribute("errorMsg","Question does not exists");
			return "redirect:questionmanager.html?id="+questionSetID;
		}
	}
	
	@RequestMapping(value = "/editquestion", method = RequestMethod.GET)
	public String geteditquestion(@ModelAttribute("question") Question question, @RequestParam("id")int id, BindingResult result, Locale locale, Map<String, Object> map) {
		logger.info("Welcome to Edit Question  page ! GET Method :The client locale is {}.", locale);		
		map.put("question", questionService.getQuestion(id));
		return "editquestion";
	}
	
	@RequestMapping(value = "/editquestion", method = RequestMethod.POST)
	public String posteditquestion(@Valid @ModelAttribute("question") Question question, BindingResult result, Locale locale, final RedirectAttributes redirectAttributes, HttpServletRequest req, Map<String, Object> map) {
		logger.info("Welcome to Edit Question page ! The client locale is {}.", locale);
		
		int id = Integer.parseInt(req.getParameter("question_set_id"));
		
		Question_Set question_set = questionSetService.getQuestionSet(id);
		
		if(!question.getQuestion().isEmpty()) {
				question.setQuestion_set(question_set);
				questionService.editQuestion(question);
				logger.debug("Question edited successfully.");
				redirectAttributes.addFlashAttribute("Msg","Question edited successfully.");
				return "redirect:questionmanager.html?id="+question_set.getId();
		} else {
			redirectAttributes.addFlashAttribute("errorMsg","Question does not exists.");
			return "redirect:editquestion.html?id="+question.getId();
		}
	}

}