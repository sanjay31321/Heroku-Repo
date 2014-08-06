package com.sanjay31321.sys.service;

import java.util.List;

import com.sanjay31321.sys.model.Feedback_Done;

public interface FeedbackDoneService {
	public List<Feedback_Done> feedbackDoneList();
	public void addFeedbackDone(Feedback_Done feedback_done);
	public void deleteFeedbackDone(int id);
	public List<Feedback_Done> getFeedbackDoneByUserId(int user_id);
	public Feedback_Done getFeedbackDoneByFeedbackId(int feedback_id);
	public Feedback_Done feedbackDoneExists(int user_id, int feedback_id);
	public int countFeedbackDone(int feedback_id);
}
