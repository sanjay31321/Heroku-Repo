package com.sanjay31321.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanjay31321.sys.dao.UserRoleDao;
import com.sanjay31321.sys.model.User_Role;
import com.sanjay31321.sys.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService{
	
	@Autowired
	private UserRoleDao userRoleDao;

	@Transactional
	public List<User_Role> userRoleList() {
		return userRoleDao.userRoleList();
	}

	@Transactional
	public void addUserRole(User_Role user_role) {
		userRoleDao.addUserRole(user_role);
	}

	@Transactional
	public void deleteUserRole(int id) {
		userRoleDao.deleteUserRole(id);
	}

	@Transactional
	public void editUserRole(User_Role user_role) {
		userRoleDao.editUserRole(user_role);
	}

	@Transactional
	public User_Role getUserRole(int id) {
		return userRoleDao.getUserRole(id);
	}
	
	@Transactional
	public void deleteAll() {
		userRoleDao.deleteAll();
	}
}
