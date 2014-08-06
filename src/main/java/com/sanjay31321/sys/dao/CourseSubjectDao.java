package com.sanjay31321.sys.dao;

import java.util.List;

import com.sanjay31321.sys.model.Course_Subject;

public interface CourseSubjectDao {
	public List<Course_Subject> courseSubjectList();
	public void addCourseSubject(Course_Subject course_subject);
	public void editCourseSubject(Course_Subject course_subject);
	public void deleteCourseSubject(int id);
	public Course_Subject getCourseSubject(int id);
	public List<Course_Subject> getCourseSubjectByCourseId(int course_id);
	public Course_Subject subjectExistsInCourse(int course_id, int subject_id);
}
