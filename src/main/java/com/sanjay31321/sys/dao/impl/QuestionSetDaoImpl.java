package com.sanjay31321.sys.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sanjay31321.sys.dao.QuestionSetDao;
import com.sanjay31321.sys.model.Question_Set;

@Repository
public class QuestionSetDaoImpl implements QuestionSetDao{

	@Autowired
	private SessionFactory session;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Question_Set> questionSetList() {
		return session.getCurrentSession().createQuery("from Question_Set").list();
	}

	@Override
	public void addQuestionSet(Question_Set question_set) {
		session.getCurrentSession().save(question_set);
	}

	@Override
	public void deleteQuestionSet(int id) {
		session.getCurrentSession().delete(getQuestionSet(id));
	}

	@Override
	public void editQuestionSet(Question_Set question_set) {
		session.getCurrentSession().update(question_set);
	}

	@Override
	public Question_Set getQuestionSet(int id) {
		return (Question_Set)session.getCurrentSession().get(Question_Set.class, id);
	}

	@Override
	public Question_Set questionSetExists(String question_set) {
		return (Question_Set) session.getCurrentSession().createQuery("from Question_Set as qs where qs.name=?").setString(0, question_set).uniqueResult();
	}
}
