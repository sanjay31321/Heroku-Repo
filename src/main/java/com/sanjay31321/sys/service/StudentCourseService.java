package com.sanjay31321.sys.service;

import java.util.List;

import com.sanjay31321.sys.model.Student_Course;

public interface StudentCourseService {
	public List<Student_Course> studentCourseList();
	public void addStudentCourse(Student_Course student_course);
	public void editStudentSubject(Student_Course student_course);
	public void deleteStudentCourse(int id);
	public Student_Course getStudentCourse(int id);
	public Student_Course getStudentCourseByUserID(int user_id);
}
