package com.sanjay31321.sys.preset.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanjay31321.sys.model.Subject;
import com.sanjay31321.sys.service.CourseService;
import com.sanjay31321.sys.service.SubjectService;

@Service
public class InsertSubject {
	
	@Autowired private SubjectService subjectService;
	@Autowired private CourseService courseService;
	
	public void insert() {
		
		Subject subject = new Subject();
		
		subject.setId(1);
		subject.setName("Data Structures & Algorithms");
		subjectService.addSubject(subject);
		
		subject.setId(2);
		subject.setName("Advanced Image Processing");
		subjectService.addSubject(subject);
		
		subject.setId(3);
		subject.setName("Software Engineering");
		subjectService.addSubject(subject);
	
		subject.setId(4);
		subject.setName("Linux Administration");
		subjectService.addSubject(subject);
		
		subject.setId(5);
		subject.setName("Marketing Management");
		subjectService.addSubject(subject);
		
		subject.setId(6);
		subject.setName("Programming Teachniques in Java");
		subjectService.addSubject(subject);
		
		subject.setId(7);
		subject.setName("Embedded Systems Design");
		subjectService.addSubject(subject);
		
		subject.setId(8);
		subject.setName("Digital Signal Processing");
		subjectService.addSubject(subject);
		
		subject.setId(9);
		subject.setName("Designing with Microcontrollers");
		subjectService.addSubject(subject);
		
		subject.setId(10);
		subject.setName("Real Time Operating Systems");
		subjectService.addSubject(subject);
	}
}
