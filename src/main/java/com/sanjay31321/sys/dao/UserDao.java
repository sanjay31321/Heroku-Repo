package com.sanjay31321.sys.dao;

import java.util.List;

import com.sanjay31321.sys.model.Course;
import com.sanjay31321.sys.model.User;

public interface UserDao {
	public User findUserForLogin(String email, String password);
	public List<User> getAllUsers();
	public User getUserByEmail(String email);
	public void updateUser(User user);
	public void deleteUser(int id);
	public Course getCourse(int id);
	public void Register(User user);
	public List<Course> getCourseList();
	public User getUser(int id);
}
