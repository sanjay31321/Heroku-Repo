package com.sanjay31321.sys.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sanjay31321.sys.dao.TeacherSubjectDao;
import com.sanjay31321.sys.model.Course_Subject;
import com.sanjay31321.sys.model.Teacher_Subject;

@Repository
public class TeacherSubjectDaoImpl implements TeacherSubjectDao{

	@Autowired
	private SessionFactory session;

	@SuppressWarnings("unchecked")
	@Override
	public List<Teacher_Subject> teacherSubjectList() {
		return session.getCurrentSession().createQuery("from Teacher_Subject").list();
	}

	@Override
	public void addTeacherSubject(Teacher_Subject teacher_subject) {
		session.getCurrentSession().save(teacher_subject);
	}

	@Override
	public void editTeacherSubject(Teacher_Subject teacher_subject) {
		session.getCurrentSession().update(teacher_subject);
	}

	@Override
	public void deleteTeacherSubject(int id) {
		session.getCurrentSession().delete(getTeacherSubject(id));
	}

	@Override
	public Teacher_Subject getTeacherSubject(int id) {
		return (Teacher_Subject)session.getCurrentSession().get(Teacher_Subject.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Teacher_Subject> getTeacherSubjectByTeacherId(int teacher_id) {
		return session.getCurrentSession().createQuery("from Teacher_Subject as tc where tc.user.id=?").setInteger(0, teacher_id).list();
	}

	@Override
	public Teacher_Subject subjectExistsWithTeacher(int teacher_id, int subject_id) {
		return  (Teacher_Subject) session.getCurrentSession().createQuery("from Teacher_Subject as tc where tc.user.id=? and tc.subject.id=?").setInteger(0, teacher_id).setInteger(1, subject_id).uniqueResult();
	}
}
