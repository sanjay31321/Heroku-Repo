package com.sanjay31321.sys.service;

import java.util.List;

import com.sanjay31321.sys.model.Course;
import com.sanjay31321.sys.model.User;

public interface UserService {
	public User findUserForLogin(String email, String password);
	public List<User> getAllUsers();
	public User userExists(String email);
	public void updateUser(User user);
	public void deleteUser(int id);
	public Course getCourse(int id);
	public List<Course> getCourseList();
	public void Register(User user);
	public User getUser(int id);
	public boolean userExist(String email);
}
