package com.sanjay31321.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanjay31321.sys.dao.AnswerDao;
import com.sanjay31321.sys.model.Answer;
import com.sanjay31321.sys.service.AnswerService;


@Service
public class AnswerServiceImpl implements AnswerService{
	
	@Autowired
	private AnswerDao answerDao;

	@Transactional
	public List<Answer> answerList() {
		return answerDao.answerList();
	}

	@Transactional
	public void addAnswer(Answer answer) {
		answerDao.addAnswer(answer);
	}

	@Transactional
	public void deleteAnswer(int id) {
		answerDao.deleteAnswer(id);
	}

	@Transactional
	public void editAnswer(Answer answer) {
		answerDao.editAnswer(answer);
	}

	@Transactional
	public Answer getAnswer(int id) {
		return answerDao.getAnswer(id);
	}

	@Transactional
	public List<Answer> getAnswerListByFeedbackId(int feedback_id) {
		return answerDao.getAnswerListByFeedbackId(feedback_id);
	}
}
