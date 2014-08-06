package com.sanjay31321.sys.service;

import java.util.List;

import com.sanjay31321.sys.model.Subject;

public interface SubjectService {
	public List<Subject> subjectList();
	public void addSubject(Subject subject);
	public void deleteSubject(int id);
	public void editSubject(Subject subject);
	public Subject getSubject(int id);
	public Subject subjectExists(String subject);
}
