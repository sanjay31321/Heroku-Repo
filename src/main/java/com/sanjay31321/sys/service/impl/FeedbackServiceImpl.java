package com.sanjay31321.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanjay31321.sys.dao.FeedbackDao;
import com.sanjay31321.sys.model.Feedback;
import com.sanjay31321.sys.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService{
	
	@Autowired
	private FeedbackDao feedbackDao;

	@Transactional
	public List<Feedback> feedbackList() {
		return feedbackDao.feedbackList();
	}

	@Transactional
	public void addFeedback(Feedback feedback) {
		feedbackDao.addFeedback(feedback);
	}

	@Transactional
	public void deleteFeedback(int id) {
		feedbackDao.deleteFeedback(id);
	}

	@Transactional
	public void editFeedback(Feedback feedback) {
		feedbackDao.editFeedback(feedback);
	}

	@Transactional
	public Feedback getFeedback(int id) {
		return feedbackDao.getFeedback(id);
	}

	@Transactional
	public Feedback getFeedback(String feedback) {
		return feedbackDao.feedbackExists(feedback);
	}

	@Transactional
	public boolean feedbackExists(String feedback) {
		Feedback feedback1 = feedbackDao.feedbackExists(feedback);
		if(feedback1 != null )
			return true;
		else
			return false;
	}

	@Transactional
	public boolean feedbackExists(int id) {
		Feedback feedback1 = feedbackDao.getFeedback(id);
		if(feedback1 != null )
			return true;
		else
			return false;
	}
}
