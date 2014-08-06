package com.sanjay31321.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanjay31321.sys.dao.FeedbackDoneDao;
import com.sanjay31321.sys.model.Feedback_Done;
import com.sanjay31321.sys.service.FeedbackDoneService;

@Service
public class FeedbackDoneServiceImpl implements FeedbackDoneService{
	
	@Autowired
	private FeedbackDoneDao feedbackDoneDao;

	@Transactional
	public List<Feedback_Done> feedbackDoneList() {
		return feedbackDoneDao.feedbackDoneList();
	}

	@Transactional
	public void addFeedbackDone(Feedback_Done feedback_done) {
		feedbackDoneDao.addFeedbackDone(feedback_done);
	}

	@Transactional
	public void deleteFeedbackDone(int id) {
		feedbackDoneDao.deleteFeedbackDone(id);
	}

	@Transactional
	public List<Feedback_Done> getFeedbackDoneByUserId(int user_id) {
		return feedbackDoneDao.getFeedbackDoneByUserId(user_id);
	}

	@Transactional
	public Feedback_Done getFeedbackDoneByFeedbackId(int feedback_id) {
		return feedbackDoneDao.getFeedbackDoneByFeedbackId(feedback_id);
	}


	@Transactional
	public Feedback_Done feedbackDoneExists(int user_id, int feedback_id) {
		return feedbackDoneDao.feedbackDoneExists(user_id, feedback_id);
	}

	@Transactional
	public int countFeedbackDone(int feedback_id) {
		List <Feedback_Done> feedbackDoneList = feedbackDoneDao.getFeedbackDoneByFeedbackID(feedback_id);
		return feedbackDoneList.size();
	}
	
}
