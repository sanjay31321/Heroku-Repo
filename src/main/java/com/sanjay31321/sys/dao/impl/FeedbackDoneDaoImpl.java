package com.sanjay31321.sys.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sanjay31321.sys.dao.FeedbackDoneDao;
import com.sanjay31321.sys.model.Feedback_Done;

@Repository
public class FeedbackDoneDaoImpl implements FeedbackDoneDao {

	@Autowired
	private SessionFactory session;

	@SuppressWarnings("unchecked")
	@Override
	public List<Feedback_Done> feedbackDoneList() {
		return session.getCurrentSession().createQuery("from Feedback_Done")
				.list();
	}

	@Override
	public void addFeedbackDone(Feedback_Done feedback_done) {
		session.getCurrentSession().save(feedback_done);
	}

	@Override
	public void deleteFeedbackDone(int id) {
		session.getCurrentSession().delete(getFeedbackDone(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Feedback_Done> getFeedbackDoneByUserId(int user_id) {
		return  session.getCurrentSession()
				.createQuery("from Feedback_Done as fd where fd.user.id=?")
				.setInteger(0, user_id).list();
	}

	@Override
	public Feedback_Done getFeedbackDoneByFeedbackId(int feedback_id) {
		return (Feedback_Done) session.getCurrentSession()
				.createQuery("from Feedback_Done as fd where fd.feedback.id=?")
				.setInteger(0, feedback_id).uniqueResult();
	}

	@Override
	public Feedback_Done getFeedbackDone(int id) {
		return (Feedback_Done) session.getCurrentSession().get(
				Feedback_Done.class, id);
	}

	@Override
	public Feedback_Done feedbackDoneExists(int user_id, int feedback_id) {
		return (Feedback_Done) session .getCurrentSession().createQuery(
						"from Feedback_Done as fd where fd.feedback.id=? and fd.user.id=?")
				.setInteger(0, feedback_id).setInteger(1, user_id)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Feedback_Done> getFeedbackDoneByFeedbackID(int feedback_id) {
		return  session.getCurrentSession()
				.createQuery("from Feedback_Done as fd where fd.feedback.id=?")
				.setInteger(0, feedback_id).list();
	}

}
