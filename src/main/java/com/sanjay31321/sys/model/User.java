package com.sanjay31321.sys.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity @Table (name="user")
public class User {
	
	@Id @Column @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotEmpty 	@Column(name="email")  @Email
	private String email;
	
	@NotEmpty  
	@Column(name="password")
	private String password;
	
	@NotEmpty
	@Transient 
	private String confirm_password;
	
	@NotEmpty
	@Column(name="name") 
	private String name;
	
	@Transient 
	private int course_id;
	
	@Column(name="security_question")
	private String security_question;
	
	@NotEmpty
	@Column(name="security_answer") 
	private String security_answer;
	
	@Column(name="active")
	private boolean active=false;
	
	@ManyToOne
    @JoinColumn(name="user_role") 
	private User_Role user_role;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Student_Course> student_course;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Feedback_Done> feedback_done;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Teacher_Subject> teacher_subject;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Answer> answer;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Feedback> feedback;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfirm_password() {
		return confirm_password;
	}
	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public String getSecurity_question() {
		return security_question;
	}
	public void setSecurity_question(String security_question) {
		this.security_question = security_question;
	}
	public String getSecurity_answer() {
		return security_answer;
	}
	public void setSecurity_answer(String security_answer) {
		this.security_answer = security_answer;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public User_Role getUser_role() {
		return user_role;
	}
	public void setUser_role(User_Role user_role) {
		this.user_role = user_role;
	}
	public List<Student_Course> getStudent_course() {
		return student_course;
	}
	public void setStudent_course(List<Student_Course> student_course) {
		this.student_course = student_course;
	}
	public List<Feedback_Done> getFeedback_done() {
		return feedback_done;
	}
	public void setFeedback_done(List<Feedback_Done> feedback_done) {
		this.feedback_done = feedback_done;
	}
	public List<Teacher_Subject> getTeacher_subject() {
		return teacher_subject;
	}
	public void setTeacher_subject(List<Teacher_Subject> teacher_subject) {
		this.teacher_subject = teacher_subject;
	}
	public List<Answer> getAnswer() {
		return answer;
	}
	public void setAnswer(List<Answer> answer) {
		this.answer = answer;
	}
	public List<Feedback> getFeedback() {
		return feedback;
	}
	public void setFeedback(List<Feedback> feedback) {
		this.feedback = feedback;
	}
}
