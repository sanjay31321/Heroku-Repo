package com.sanjay31321.sys.preset;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sanjay31321.sys.preset.data.InsertCourse;
import com.sanjay31321.sys.preset.data.InsertCourseSubject;
import com.sanjay31321.sys.preset.data.InsertFeedback;
import com.sanjay31321.sys.preset.data.InsertQuestion;
import com.sanjay31321.sys.preset.data.InsertQuestionSet;
import com.sanjay31321.sys.preset.data.InsertStudentCourse;
import com.sanjay31321.sys.preset.data.InsertSubject;
import com.sanjay31321.sys.preset.data.InsertTeacherSubject;
import com.sanjay31321.sys.preset.data.InsertUser;
import com.sanjay31321.sys.preset.data.InsertUserRole;

@Component
public class DefaultDataInstall {
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultDataInstall.class);
	
	@Autowired private InsertUserRole role;
	@Autowired private InsertCourse course;
	@Autowired private InsertQuestionSet question_set;
	@Autowired private InsertUser user;
	@Autowired private InsertSubject subject;
	@Autowired private InsertQuestion question;
	@Autowired private InsertFeedback feedback;
	@Autowired private InsertCourseSubject course_subject;
	@Autowired private InsertTeacherSubject teacher_subject;
	@Autowired private InsertStudentCourse student_course;
	
	public void install()  {
		
		role.insert();
		logger.info("User Role data is installed");
		
		course.insert();
		logger.info("Course data is installed");
				
		question_set.insert();
		logger.info("Question Set data is installed");
		
		user.insert();
		logger.info("User data is installed");
		
		subject.insert();
		logger.info("Subject data is installed");
				
		question.insert();
		logger.info("Question data is installed");
		
		feedback.insert();
		logger.info("Feedback data is installed");
		
		course_subject.insert();
		logger.info("Course_Subject data is installed");
		
		teacher_subject.insert();
		logger.info("Teacher_Subject data is installed");
		
		student_course.insert();
		logger.info("Student_Course data is installed");
		
	}
}
