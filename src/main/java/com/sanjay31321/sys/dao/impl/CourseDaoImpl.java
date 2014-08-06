package com.sanjay31321.sys.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sanjay31321.sys.dao.CourseDao;
import com.sanjay31321.sys.model.Course;

@Repository
public class CourseDaoImpl implements CourseDao{

	@Autowired
	private SessionFactory session;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> courseList() {
		return session.getCurrentSession().createQuery("from Course").list();
	}

	@Override
	public void addCourse(Course course) {
		session.getCurrentSession().save(course);
	}

	@Override
	public void deleteCourse(int id) {
		session.getCurrentSession().delete(getCourse(id));
	}

	@Override
	public void editCourse(Course course) {
		session.getCurrentSession().update(course);
	}

	@Override
	public Course getCourse(int id) {
		return (Course)session.getCurrentSession().get(Course.class, id);
	}

	@Override
	public Course courseExists(String course) {
		return (Course) session.getCurrentSession().createQuery("from Course as c where c.name=?").setString(0, course).uniqueResult();
	}
}
