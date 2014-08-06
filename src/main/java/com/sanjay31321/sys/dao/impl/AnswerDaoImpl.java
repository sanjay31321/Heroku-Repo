package com.sanjay31321.sys.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sanjay31321.sys.dao.AnswerDao;
import com.sanjay31321.sys.model.Answer;
import com.sanjay31321.sys.model.Course;

@Repository
public class AnswerDaoImpl implements AnswerDao{

	@Autowired
	private SessionFactory session;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Answer> answerList() {
		return session.getCurrentSession().createQuery("from Answer").list();
	}

	@Override
	public void addAnswer(Answer answer) {
		session.getCurrentSession().save(answer);
	}

	@Override
	public void deleteAnswer(int id) {
		session.getCurrentSession().delete(getAnswer(id));
	}

	@Override
	public void editAnswer(Answer answer) {
		session.getCurrentSession().update(answer);
	}

	@Override
	public Answer getAnswer(int id) {
		return (Answer)session.getCurrentSession().get(Answer.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Answer> getAnswerListByFeedbackId(int feedback_id) {
		return session.getCurrentSession().createQuery("from Answer as a where a.feedback.id=?").setInteger(0, feedback_id).list();
	}
}
