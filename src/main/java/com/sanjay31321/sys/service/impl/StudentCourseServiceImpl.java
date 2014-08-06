package com.sanjay31321.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanjay31321.sys.dao.StudentCourseDao;
import com.sanjay31321.sys.model.Student_Course;
import com.sanjay31321.sys.service.StudentCourseService;

@Service
public class StudentCourseServiceImpl implements StudentCourseService{
	
	@Autowired
	private StudentCourseDao studentCourseDao;

	@Transactional
	public List<Student_Course> studentCourseList() {
		return studentCourseDao.studentCourseList();
	}

	@Transactional
	public void addStudentCourse(Student_Course student_course) {
		studentCourseDao.addStudentCourse(student_course);
	}

	@Transactional
	public void deleteStudentCourse(int id) {
		studentCourseDao.deleteStudentCourse(id);
	}

	@Transactional
	public void editStudentSubject(Student_Course student_course) {
		studentCourseDao.editStudentSubject(student_course);
	}

	@Transactional
	public Student_Course getStudentCourse(int id) {
		return studentCourseDao.getStudentCourse(id);
	}

	@Transactional
	public Student_Course getStudentCourseByUserID(int user_id) {
		return studentCourseDao.getStudentCourseByUserID(user_id);
	}

}
