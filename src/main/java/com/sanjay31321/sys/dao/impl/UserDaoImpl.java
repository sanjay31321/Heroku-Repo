package com.sanjay31321.sys.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sanjay31321.sys.dao.UserDao;
import com.sanjay31321.sys.model.Course;
import com.sanjay31321.sys.model.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private SessionFactory session;
	
	@Override
	public User findUserForLogin(String email, String password) {
		return (User) session.getCurrentSession().createQuery("from User as u where u.email=? and u.password=?")
				.setString(0, email).setString(1, password).uniqueResult();	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		return session.getCurrentSession().createQuery("from User").list();
	}

	@Override
	public User getUserByEmail(String email) {
		return (User) session.getCurrentSession().createQuery("from User as u where u.email=?").setString(0, email).uniqueResult();
	}

	@Override
	public Course getCourse(int id) {
		return (Course) session.getCurrentSession().createQuery("from Course as c where c.id=?").setInteger(0, id).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getCourseList() {
		return session.getCurrentSession().createQuery("from Course").list();
	}

	@Override
	public void Register(User user) {
		session.getCurrentSession().save(user);
	}

	@Override
	public void updateUser(User user) {
		session.getCurrentSession().update(user);
	}

	@Override
	public User getUser(int id) {
		return (User)session.getCurrentSession().get(User.class, id);
	}

	@Override
	public void deleteUser(int id) {
		session.getCurrentSession().delete(getUser(id));
	}
}
