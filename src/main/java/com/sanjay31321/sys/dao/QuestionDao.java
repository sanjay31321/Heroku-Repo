package com.sanjay31321.sys.dao;

import java.util.List;

import com.sanjay31321.sys.model.Question;

public interface QuestionDao {
	public List<Question> questionList();
	public void addQuestion(Question question);
	public void deleteQuestion(int id);
	public void editQuestion(Question question);
	public Question getQuestion(int id);
	public Question questionExists(String question);
	public List<Question> getQuestionByQuestionSetID(int question_set_id);
}
