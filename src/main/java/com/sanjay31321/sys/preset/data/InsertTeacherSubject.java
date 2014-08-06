package com.sanjay31321.sys.preset.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanjay31321.sys.model.Teacher_Subject;
import com.sanjay31321.sys.service.SubjectService;
import com.sanjay31321.sys.service.TeacherSubjectService;
import com.sanjay31321.sys.service.UserService;

@Service
public class InsertTeacherSubject {
	
	@Autowired private TeacherSubjectService teacherSubjectService;
	@Autowired private UserService userService;
	@Autowired private SubjectService subjectService;
	
	public void insert() {
		Teacher_Subject teacher_subject = new Teacher_Subject();
		
		teacher_subject.setId(1);
		teacher_subject.setUser(userService.getUser(2));
		teacher_subject.setSubject(subjectService.getSubject(1));
		teacherSubjectService.addTeacherSubject(teacher_subject);
	}
}
