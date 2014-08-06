package com.sanjay31321.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanjay31321.sys.dao.SubjectDao;
import com.sanjay31321.sys.model.Subject;
import com.sanjay31321.sys.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService{
	
	@Autowired
	private SubjectDao subjectDao;

	@Transactional
	public List<Subject> subjectList() {
		return subjectDao.subjectList();
	}

	@Transactional
	public void addSubject(Subject subject) {
		subjectDao.addSubject(subject);
	}

	@Transactional
	public void deleteSubject(int id) {
		subjectDao.deleteSubject(id);
	}

	@Transactional
	public void editSubject(Subject subject) {
		subjectDao.editSubject(subject);
	}

	@Transactional
	public Subject getSubject(int id) {
		return subjectDao.getSubject(id);
	}

	@Transactional
	public Subject subjectExists(String subject) {
		return subjectDao.subjectExists(subject);
	}
}
