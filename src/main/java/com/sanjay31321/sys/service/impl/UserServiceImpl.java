package com.sanjay31321.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanjay31321.sys.dao.UserDao;
import com.sanjay31321.sys.model.Course;
import com.sanjay31321.sys.model.User;
import com.sanjay31321.sys.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Transactional
	public User findUserForLogin(String email, String password) {
		return userDao.findUserForLogin(email, password);
	}

	@Transactional
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Transactional
	public User userExists(String email) {
		return userDao.getUserByEmail(email);
	}

	@Transactional
	public Course getCourse(int id) {
		return userDao.getCourse(id);
	}

	@Transactional
	public List<Course> getCourseList() {
		return userDao.getCourseList();
	}

	@Transactional
	public void Register(User user) {
		userDao.Register(user);
	}

	@Transactional
	public void updateUser(User user) {
		userDao.updateUser(user);	
	}

	@Transactional
	public User getUser(int id) {
		return userDao.getUser(id);
	}

	@Transactional
	public void deleteUser(int id) {
		userDao.deleteUser(id);
	}

	@Transactional
	public boolean userExist(String email) {
		User user = userDao.getUserByEmail(email);
		if(user == null) return false;
			else return true;
	}

}
