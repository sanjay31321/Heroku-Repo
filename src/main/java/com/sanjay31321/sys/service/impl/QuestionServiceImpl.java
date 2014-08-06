package com.sanjay31321.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanjay31321.sys.dao.QuestionDao;
import com.sanjay31321.sys.model.Question;
import com.sanjay31321.sys.service.QuestionService;



@Service
public class QuestionServiceImpl implements QuestionService{
	
	@Autowired
	private QuestionDao questionDao;

	@Transactional
	public List<Question> questionList() {
		return questionDao.questionList();
	}

	@Transactional
	public void addQuestion(Question question) {
		questionDao.addQuestion(question);
	}

	@Transactional
	public void deleteQuestion(int id) {
		questionDao.deleteQuestion(id);
	}

	@Transactional
	public void editQuestion(Question question) {
		questionDao.editQuestion(question);
	}

	@Transactional
	public Question getQuestion(int id) {
		return questionDao.getQuestion(id);
	}

	@Transactional
	public Question questionExists(String question) {
		return questionDao.questionExists(question);
	}

	@Transactional
	public List<Question> getQuestionByQuestionSetID(int question_set_id) {
		return questionDao.getQuestionByQuestionSetID(question_set_id);
	}

	@Transactional
	public boolean questionExistsByQuestion(String question) {
		if(questionDao.questionExists(question) != null) return true;
			else return false;
	}

	@Transactional
	public boolean questionExistsById(int id) {
		if(questionDao.getQuestion(id) != null) return true;
			else return false;
	}
}
