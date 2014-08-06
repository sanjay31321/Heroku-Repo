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

@Entity @Table(name="subject")
public class Subject {
	
	@Id @Column @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="subject_name")
	private String name;
	
	@OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Course_Subject> course_subject;
	
	@OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Teacher_Subject> teacher_subject;
	
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
	public List<Teacher_Subject> getTeacher_subject() {
		return teacher_subject;
	}
	public void setTeacher_subject(List<Teacher_Subject> teacher_subject) {
		this.teacher_subject = teacher_subject;
	}
}
