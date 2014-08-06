package com.sanjay31321.sys.preset.data;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanjay31321.sys.model.Feedback;
import com.sanjay31321.sys.service.FeedbackService;
import com.sanjay31321.sys.service.QuestionSetService;
import com.sanjay31321.sys.service.SubjectService;
import com.sanjay31321.sys.service.UserService;

@Service
public class InsertFeedback {
	
	@Autowired private FeedbackService feedbackService;
	@Autowired private UserService userService;
	@Autowired private QuestionSetService questionSetService;
	@Autowired private SubjectService subjectService;
	
	public void insert() {
		Feedback feedback = new Feedback();
		Calendar cal= Calendar.getInstance(); 
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 10);  
		
		feedback.setId(1);
		feedback.setName("Feedback for Data Structures & Algorithms");
		feedback.setQuestion_set(questionSetService.getQuestionSet(1));
		feedback.setSubject(subjectService.getSubject(1));
		feedback.setCreated();
		feedback.setDate_from(new Date());
		feedback.setDate_to(cal.getTime());
		feedback.setUser(userService.getUser(2));
		feedbackService.addFeedback(feedback);
	}
}
