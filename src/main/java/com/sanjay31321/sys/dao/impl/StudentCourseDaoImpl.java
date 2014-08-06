package com.sanjay31321.sys.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sanjay31321.sys.dao.StudentCourseDao;
import com.sanjay31321.sys.model.Student_Course;

@Repository
public class StudentCourseDaoImpl implements StudentCourseDao{

	@Autowired
	private SessionFactory session;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Student_Course> studentCourseList() {
		return session.getCurrentSession().createQuery("from Student_Course").list();
	}

	@Override
	public void addStudentCourse(Student_Course student_course) {
		session.getCurrentSession().save(student_course);
	}

	@Override
	public void deleteStudentCourse(int id) {
		session.getCurrentSession().delete(getStudentCourse(id));
	}

	@Override
	public void editStudentSubject(Student_Course student_course) {
		session.getCurrentSession().update(student_course);
	}

	@Override
	public Student_Course getStudentCourse(int id) {
		return (Student_Course)session.getCurrentSession().get(Student_Course.class, id);
	}

	@Override
	public Student_Course getStudentCourseByUserID(int user_id) {
		return (Student_Course) session.getCurrentSession().createQuery("from Student_Course as sc where sc.user.id=?").setInteger(0, user_id).uniqueResult();
	}

}
