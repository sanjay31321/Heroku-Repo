package com.sanjay31321.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanjay31321.sys.dao.CourseDao;
import com.sanjay31321.sys.model.Course;
import com.sanjay31321.sys.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseDao courseDao;

	@Transactional
	public List<Course> courseList() {
		return courseDao.courseList();
	}

	@Transactional
	public void addCourse(Course course) {
		courseDao.addCourse(course);
	}

	@Transactional
	public void deleteCourse(int id) {
		courseDao.deleteCourse(id);
	}

	@Transactional
	public void editCourse(Course course) {
		courseDao.editCourse(course);
	}

	@Transactional
	public Course courseExists(String course) {
		return courseDao.courseExists(course);
	}

	@Transactional
	public Course getCourse(int id) {
		return courseDao.getCourse(id);
	}

	@Transactional
	public boolean courseExist(String course) {
		Course course1 = courseDao.courseExists(course);
		if(course1 != null ) return true;
		else return false;
	}

	@Transactional
	public boolean courseExistsById(int id) {
		Course course1 = courseDao.getCourse(id);
		if(course1 != null ) return true;
		else return false;
	}
}
