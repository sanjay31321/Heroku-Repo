package com.sanjay31321.sys.service;

import java.util.List;

import com.sanjay31321.sys.model.Course_Subject;

public interface CourseSubjectService {
	public List<Course_Subject> courseSubjectList();
	public void addCourseSubject(Course_Subject course_subject);
	public void deleteCourseSubject(int id);
	public void editCourseSubject(Course_Subject course_subject);
	public Course_Subject getCourseSubject(int id);
	public List<Course_Subject> getCourseSubjectByCourseId(int course_id);
	public boolean subjectExistsInCourse(int course_id, int subject_id);
	public Course_Subject subjectExistsInCourseID(int course_id, int subject_id);
}
