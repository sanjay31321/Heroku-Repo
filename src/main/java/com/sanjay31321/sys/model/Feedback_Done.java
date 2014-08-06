package com.sanjay31321.sys.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity @Table (name="feedback_done")
public class Feedback_Done {
	
	@Id @Column @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd.MM.yyyy hh:mm")
    @Column(name = "created")
    private Date created;

	@ManyToOne
    @JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
    @JoinColumn(name="feedback_id")
	private Feedback feedback;
	
	
	public Date getCreated() {
		return created;
	}
	public void setCreated() {
		this.created = new Date();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Feedback getFeedback() {
		return feedback;
	}
	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}
}
