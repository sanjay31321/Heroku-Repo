package com.sanjay31321.sys.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sanjay31321.sys.dao.SubjectDao;
import com.sanjay31321.sys.model.Subject;

@Repository
public class SubjectDaoImpl implements SubjectDao{

	@Autowired
	private SessionFactory session;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Subject> subjectList() {
		return session.getCurrentSession().createQuery("from Subject").list();
	}

	@Override
	public void addSubject(Subject subject) {
		session.getCurrentSession().save(subject);
	}

	@Override
	public void deleteSubject(int id) {
		session.getCurrentSession().delete(getSubject(id));
	}

	@Override
	public void editSubject(Subject subject) {
		session.getCurrentSession().update(subject);
	}

	@Override
	public Subject getSubject(int id) {
		return (Subject)session.getCurrentSession().get(Subject.class, id);
	}

	@Override
	public Subject subjectExists(String subject) {
		return (Subject) session.getCurrentSession().createQuery("from Subject as s where s.name=?").setString(0, subject).uniqueResult();
	}
}
