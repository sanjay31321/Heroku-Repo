package com.sanjay31321.sys.dao;

import java.util.List;

import com.sanjay31321.sys.model.Course;

public interface CourseDao {
	public List<Course> courseList();
	public void addCourse(Course course);
	public void deleteCourse(int id);
	public void editCourse(Course course);
	public Course getCourse(int id);
	public Course courseExists(String course);
}
