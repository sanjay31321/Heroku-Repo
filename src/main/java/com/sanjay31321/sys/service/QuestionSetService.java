package com.sanjay31321.sys.service;

import java.util.List;

import com.sanjay31321.sys.model.Question_Set;

public interface QuestionSetService {
	public List<Question_Set> questionSetList();
	public void addQuestionSet(Question_Set question_set);
	public void deleteQuestionSet(int id);
	public void editQuestionSet(Question_Set question_set);
	public Question_Set getQuestionSet(int id);
	public Question_Set questionSetExists(String question_set);
	public boolean questionSetExistsByName(String question_set);
	public boolean questionSetExistsById(int id);
}
