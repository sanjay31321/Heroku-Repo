package com.sanjay31321.sys.dao;

import java.util.List;

import com.sanjay31321.sys.model.Feedback;


public interface FeedbackDao {
	public List<Feedback> feedbackList();
	public void addFeedback(Feedback feedback);
	public void deleteFeedback(int id);
	public void editFeedback(Feedback feedback);
	public Feedback getFeedback(int id);
	public Feedback feedbackExists(String feedback);
}
