package com.sanjay31321.sys.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity @Table(name="feedback")
public class Feedback {
	
	@Id @Column @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotEmpty
	@Column(name="feedback_name")
	private String name;
	
	@Column(name = "attempted")
	private int attempted;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd.MM.yyyy")
    @Column(name = "created")
	private Date created;
	
	@DateTimeFormat(pattern="dd.MM.yyyy")
    @Column(name = "date_from")
	private Date date_from;

	@DateTimeFormat(pattern="dd.MM.yyyy")
    @Column(name = "date_to")
	private Date date_to;
	
	@ManyToOne
    @JoinColumn(name="subject_id")
	private Subject subject;
	
	@ManyToOne
    @JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
    @JoinColumn(name="question_set_id")
	private Question_Set question_set;
	
	@OneToMany(mappedBy = "feedback", cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Feedback_Done> feedback_done;
	
	@OneToMany(mappedBy = "feedback", cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Answer> answer;
	
	
	public void setCreated(Date created) {
		this.created = created;
	}
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
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Question_Set getQuestion_set() {
		return question_set;
	}
	public void setQuestion_set(Question_Set question_set) {
		this.question_set = question_set;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated() {
		//String date = new Date().toString();
		this.created = new Date();//new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(date);;
	}
	public Date getDate_from() {
		return date_from;
	}
	public void setDate_from(Date date_from) {
		this.date_from = date_from;
	}
	public Date getDate_to() {
		return date_to;
	}
	public void setDate_to(Date date_to) {
		this.date_to = date_to;
	}
	public int getAttempted() {
		return attempted;
	}
	public void setAttempted(int attempted) {
		this.attempted = attempted;
	}
}
