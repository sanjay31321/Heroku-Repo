package com.sanjay31321.sys.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

@Entity @Table (name="course")
public class Course {
	
	@Id @Column @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotEmpty
	@Column(name="course_name")
	private String name;
	
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Course_Subject> course_subject;
	
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Student_Course> student_course;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Course_Subject> getCourse_subject() {
		return course_subject;
	}
	public void setCourse_subject(List<Course_Subject> course_subject) {
		this.course_subject = course_subject;
	}
	public List<Student_Course> getStudent_course() {
		return student_course;
	}
	public void setStudent_course(List<Student_Course> student_course) {
		this.student_course = student_course;
	}
	
}
