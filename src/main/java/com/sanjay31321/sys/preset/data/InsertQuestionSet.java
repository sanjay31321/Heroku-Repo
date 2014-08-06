package com.sanjay31321.sys.preset.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanjay31321.sys.model.Question_Set;
import com.sanjay31321.sys.service.QuestionSetService;

@Service
public class InsertQuestionSet {
	
	@Autowired private QuestionSetService questionSetService;
	
	public void insert(){
		Question_Set question_set = new Question_Set();
		
		question_set.setId(1);
		question_set.setName("Question Set 1");
		questionSetService.addQuestionSet(question_set);
	}
}
