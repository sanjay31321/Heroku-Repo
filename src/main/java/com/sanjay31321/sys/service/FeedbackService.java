package com.sanjay31321.sys.service;

import java.util.List;


import com.sanjay31321.sys.model.Feedback;

public interface FeedbackService {
	public List<Feedback> feedbackList();
	public void addFeedback(Feedback feedback);
	public void deleteFeedback(int id);
	public void editFeedback(Feedback feedback);
	public Feedback getFeedback(int id);
	public Feedback getFeedback(String feedback);
	public boolean feedbackExists(String feedback);
	public boolean feedbackExists(int id);
}
