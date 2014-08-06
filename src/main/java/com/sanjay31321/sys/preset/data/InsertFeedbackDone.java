package com.sanjay31321.sys.preset.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanjay31321.sys.model.Feedback_Done;
import com.sanjay31321.sys.service.FeedbackDoneService;
import com.sanjay31321.sys.service.FeedbackService;
import com.sanjay31321.sys.service.UserService;

@Service
public class InsertFeedbackDone {
	
	@Autowired private FeedbackDoneService feedbackDoneService;
	@Autowired private UserService userService;
	@Autowired private FeedbackService feedbackService;
	
	public void insert() {
		Feedback_Done feedback_done = new Feedback_Done();
		
		feedback_done.setId(1);
		feedback_done.setUser(userService.getUser(5));
		feedback_done.setFeedback(feedbackService.getFeedback(1));
		feedback_done.setCreated();
		feedbackDoneService.addFeedbackDone(feedback_done);
	}
}
