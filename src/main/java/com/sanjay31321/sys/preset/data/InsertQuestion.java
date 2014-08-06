package com.sanjay31321.sys.preset.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanjay31321.sys.model.Question;
import com.sanjay31321.sys.service.QuestionService;
import com.sanjay31321.sys.service.QuestionSetService;

@Service
public class InsertQuestion {
	
	@Autowired private QuestionService questionService;
	
	@Autowired private QuestionSetService questionSetService;
	
	public void insert() {
		Question question = new Question();
		
		question.setId(1);
		question.setQuestion("The teacher speaks clearly.");
		question.setQuestion_set(questionSetService.getQuestionSet(1));
		questionService.addQuestion(question);
		
		question.setId(2);
		question.setQuestion("The teacher treats all the students equally.");
		question.setQuestion_set(questionSetService.getQuestionSet(1));
		questionService.addQuestion(question);
		
		question.setId(3);
		question.setQuestion("The teacher is enthusiastic.");
		question.setQuestion_set(questionSetService.getQuestionSet(1));
		questionService.addQuestion(question);
		
		question.setId(4);
		question.setQuestion("The teacher prepares well for class.");
		question.setQuestion_set(questionSetService.getQuestionSet(1));
		questionService.addQuestion(question);
		
		question.setId(5);
		question.setQuestion("The teacher treats all the students equally.");
		question.setQuestion_set(questionSetService.getQuestionSet(1));
		questionService.addQuestion(question);
		
		question.setId(6);
		question.setQuestion("The teacher uses effective classroom management.");
		question.setQuestion_set(questionSetService.getQuestionSet(1));
		questionService.addQuestion(question);
		
		question.setId(7);
		question.setQuestion("There are opportunities to speak English.");
		question.setQuestion_set(questionSetService.getQuestionSet(1));
		questionService.addQuestion(question);
		
	}
}
