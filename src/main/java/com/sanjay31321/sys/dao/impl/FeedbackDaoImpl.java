package com.sanjay31321.sys.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sanjay31321.sys.dao.FeedbackDao;
import com.sanjay31321.sys.model.Feedback;


@Repository
public class FeedbackDaoImpl implements FeedbackDao{

	@Autowired
	private SessionFactory session;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Feedback> feedbackList() {
		return session.getCurrentSession().createQuery("from Feedback order by id desc").list();
	}

	@Override
	public void addFeedback(Feedback feedback) {
		session.getCurrentSession().save(feedback);
	}

	@Override
	public void deleteFeedback(int id) {
		session.getCurrentSession().delete(getFeedback(id));
	}

	@Override
	public void editFeedback(Feedback feedback) {
		session.getCurrentSession().update(feedback);
	}

	@Override
	public Feedback getFeedback(int id) {
		return (Feedback)session.getCurrentSession().get(Feedback.class, id);
	}

	@Override
	public Feedback feedbackExists(String feedback) {
		return (Feedback) session.getCurrentSession().createQuery("from Feedback as f where f.name=?").setString(0, feedback).uniqueResult();
	}
}
