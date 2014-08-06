package com.sanjay31321.sys.service;

import java.util.List;

import com.sanjay31321.sys.model.Course;

public interface CourseService {
	public List<Course> courseList();
	public void addCourse(Course course);
	public void deleteCourse(int id);
	public void editCourse(Course course);
	public Course courseExists(String course);
	public Course getCourse(int id);
	public boolean courseExist(String course);
	public boolean courseExistsById(int id);
}
