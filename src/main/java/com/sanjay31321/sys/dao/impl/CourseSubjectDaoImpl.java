package com.sanjay31321.sys.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sanjay31321.sys.dao.CourseSubjectDao;
import com.sanjay31321.sys.model.Course_Subject;

@Repository
public class CourseSubjectDaoImpl implements CourseSubjectDao{

	@Autowired
	private SessionFactory session;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Course_Subject> courseSubjectList() {
		return session.getCurrentSession().createQuery("from Course_Subject").list();
	}

	@Override
	public void addCourseSubject(Course_Subject course_subject) {
		session.getCurrentSession().save(course_subject);
	}

	@Override
	public void deleteCourseSubject(int id) {
		session.getCurrentSession().delete(getCourseSubject(id));
	}

	@Override
	public void editCourseSubject(Course_Subject course_subject) {
		session.getCurrentSession().update(course_subject);
	}

	@Override
	public Course_Subject getCourseSubject(int id) {
		return (Course_Subject)session.getCurrentSession().get(Course_Subject.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course_Subject> getCourseSubjectByCourseId(int course_id) {
		return  session.getCurrentSession().createQuery("from Course_Subject as sc where sc.course.id=?").setInteger(0, course_id).list();
	}

	@Override
	public Course_Subject subjectExistsInCourse(int course_id, int subject_id) {
		return  (Course_Subject) session.getCurrentSession().createQuery("from Course_Subject as sc where sc.course.id=? and sc.subject.id=?").setInteger(0, course_id).setInteger(1, subject_id).uniqueResult();
	}

}
