package com.sanjay31321.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanjay31321.sys.dao.QuestionSetDao;
import com.sanjay31321.sys.model.Question_Set;
import com.sanjay31321.sys.service.QuestionSetService;


@Service
public class QuestionSetServiceImpl implements QuestionSetService{
	
	@Autowired
	private QuestionSetDao questionSetDao;

	@Transactional
	public List<Question_Set> questionSetList() {
		return questionSetDao.questionSetList();
	}

	@Transactional
	public void addQuestionSet(Question_Set question_set) {
		questionSetDao.addQuestionSet(question_set);
	}

	@Transactional
	public void deleteQuestionSet(int id) {
		questionSetDao.deleteQuestionSet(id);
	}

	@Transactional
	public void editQuestionSet(Question_Set question_set) {
		questionSetDao.editQuestionSet(question_set);
	}

	@Transactional
	public Question_Set getQuestionSet(int id) {
		return questionSetDao.getQuestionSet(id);
	}

	@Transactional
	public Question_Set questionSetExists(String question_set) {
		return questionSetDao.questionSetExists(question_set);
	}

	@Transactional
	public boolean questionSetExistsByName(String question_set) {
		if(questionSetDao.questionSetExists(question_set) != null) 	return true;
		else return false;
	}

	@Transactional
	public boolean questionSetExistsById(int id) {
		if(questionSetDao.getQuestionSet(id) != null) return true;
		else return false;
	}
}
