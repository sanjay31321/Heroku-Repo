package com.sanjay31321.sys.service;

import java.util.List;

import com.sanjay31321.sys.model.Question;

public interface QuestionService {
	public List<Question> questionList();
	public void addQuestion(Question question);
	public void deleteQuestion(int id);
	public void editQuestion(Question question);
	public Question getQuestion(int id);
	public Question questionExists(String question);
	public List<Question> getQuestionByQuestionSetID(int question_set_id);
	public boolean questionExistsByQuestion(String question);
	public boolean questionExistsById(int id);
}
