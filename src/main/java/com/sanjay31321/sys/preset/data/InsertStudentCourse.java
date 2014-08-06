package com.sanjay31321.sys.preset.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanjay31321.sys.model.Student_Course;
import com.sanjay31321.sys.service.CourseService;
import com.sanjay31321.sys.service.StudentCourseService;
import com.sanjay31321.sys.service.UserService;

@Service
public class InsertStudentCourse {
	
	@Autowired private StudentCourseService studentCourseService;
	@Autowired private CourseService courseService;
	@Autowired private UserService userService;
	
	public void insert() {
		Student_Course student_course = new Student_Course();
		
		student_course.setId(1);
		student_course.setCourse(courseService.getCourse(1));
		student_course.setUser(userService.getUser(4));
		studentCourseService.addStudentCourse(student_course);
		
		student_course.setId(2);
		student_course.setCourse(courseService.getCourse(1));
		student_course.setUser(userService.getUser(5));
		studentCourseService.addStudentCourse(student_course);
		
		student_course.setId(3);
		student_course.setCourse(courseService.getCourse(2));
		student_course.setUser(userService.getUser(6));
		studentCourseService.addStudentCourse(student_course);
		
		student_course.setId(4);
		student_course.setCourse(courseService.getCourse(3));
		student_course.setUser(userService.getUser(7));
		studentCourseService.addStudentCourse(student_course);
		
		student_course.setId(5);
		student_course.setCourse(courseService.getCourse(4));
		student_course.setUser(userService.getUser(8));
		studentCourseService.addStudentCourse(student_course);
	}
}
