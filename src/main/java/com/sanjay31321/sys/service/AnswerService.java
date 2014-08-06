package com.sanjay31321.sys.service;

import java.util.List;

import com.sanjay31321.sys.model.Answer;

public interface AnswerService {
	public List<Answer> answerList();
	public void addAnswer(Answer answer);
	public void deleteAnswer(int id);
	public void editAnswer(Answer answer);
	public Answer getAnswer(int id);
	public List<Answer> getAnswerListByFeedbackId(int feedback_id);
}
