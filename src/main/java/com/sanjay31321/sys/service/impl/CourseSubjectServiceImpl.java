package com.sanjay31321.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanjay31321.sys.dao.CourseSubjectDao;
import com.sanjay31321.sys.model.Course_Subject;
import com.sanjay31321.sys.service.CourseSubjectService;

@Service
public class CourseSubjectServiceImpl implements CourseSubjectService{
	
	@Autowired
	private CourseSubjectDao courseSubjectDao;

	@Transactional
	public List<Course_Subject> courseSubjectList() {
		return courseSubjectDao.courseSubjectList();
	}

	@Transactional
	public void addCourseSubject(Course_Subject course_subject) {
		courseSubjectDao.addCourseSubject(course_subject);
	}

	@Transactional
	public void deleteCourseSubject(int id) {
		courseSubjectDao.deleteCourseSubject(id);
	}

	@Transactional
	public void editCourseSubject(Course_Subject course_subject) {
		courseSubjectDao.editCourseSubject(course_subject);
	}

	@Transactional
	public Course_Subject getCourseSubject(int id) {
		return courseSubjectDao.getCourseSubject(id);
	}

	@Transactional
	public List<Course_Subject> getCourseSubjectByCourseId(int course_id) {
		return courseSubjectDao.getCourseSubjectByCourseId(course_id);
	}

	@Transactional
	public boolean subjectExistsInCourse(int course_id, int subject_id) {
		Course_Subject course_subject = courseSubjectDao.subjectExistsInCourse(course_id, subject_id);
		
		if(course_subject == null) return false;
			else return true;
	}

	@Transactional
	public Course_Subject subjectExistsInCourseID(int course_id, int subject_id) {
		return courseSubjectDao.subjectExistsInCourse(course_id, subject_id);
	}

}
