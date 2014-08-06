package com.sanjay31321.sys.preset.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanjay31321.sys.model.Course_Subject;
import com.sanjay31321.sys.service.CourseService;
import com.sanjay31321.sys.service.CourseSubjectService;
import com.sanjay31321.sys.service.SubjectService;

@Service
public class InsertCourseSubject {
	
	@Autowired private CourseSubjectService courseSubjectService;
	@Autowired private CourseService courseService;
	@Autowired private SubjectService subjectService;
	
	public void insert() {
		Course_Subject course_subject = new Course_Subject();
		
		course_subject.setId(1);
		course_subject.setCourse(courseService.getCourse(1));
		course_subject.setSubject(subjectService.getSubject(1));
		courseSubjectService.addCourseSubject(course_subject);
		
		course_subject.setId(2);
		course_subject.setCourse(courseService.getCourse(1));
		course_subject.setSubject(subjectService.getSubject(2));
		courseSubjectService.addCourseSubject(course_subject);
		
		course_subject.setId(3);
		course_subject.setCourse(courseService.getCourse(1));
		course_subject.setSubject(subjectService.getSubject(3));
		courseSubjectService.addCourseSubject(course_subject);
		
		course_subject.setId(4);
		course_subject.setCourse(courseService.getCourse(2));
		course_subject.setSubject(subjectService.getSubject(4));
		courseSubjectService.addCourseSubject(course_subject);
		
		course_subject.setId(5);
		course_subject.setCourse(courseService.getCourse(2));
		course_subject.setSubject(subjectService.getSubject(5));
		courseSubjectService.addCourseSubject(course_subject);
		
		course_subject.setId(6);
		course_subject.setCourse(courseService.getCourse(3));
		course_subject.setSubject(subjectService.getSubject(6));
		courseSubjectService.addCourseSubject(course_subject);
		
		course_subject.setId(7);
		course_subject.setCourse(courseService.getCourse(3));
		course_subject.setSubject(subjectService.getSubject(7));
		courseSubjectService.addCourseSubject(course_subject);
		
		course_subject.setId(8);
		course_subject.setCourse(courseService.getCourse(4));
		course_subject.setSubject(subjectService.getSubject(8));
		courseSubjectService.addCourseSubject(course_subject);
		
		course_subject.setId(9);
		course_subject.setCourse(courseService.getCourse(4));
		course_subject.setSubject(subjectService.getSubject(9));
		courseSubjectService.addCourseSubject(course_subject);
		
		course_subject.setId(10);
		course_subject.setCourse(courseService.getCourse(4));
		course_subject.setSubject(subjectService.getSubject(10));
		courseSubjectService.addCourseSubject(course_subject);
	}
}
