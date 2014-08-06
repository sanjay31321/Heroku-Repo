package com.sanjay31321.sys.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sanjay31321.sys.dao.UserRoleDao;
import com.sanjay31321.sys.model.User_Role;


@Repository
public class UserRoleDaoImpl implements UserRoleDao{

	@Autowired
	private SessionFactory session;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User_Role> userRoleList() {
		return session.getCurrentSession().createQuery("from User_Role").list();
	}

	@Override
	public void addUserRole(User_Role user_role) {
		session.getCurrentSession().save(user_role);
	}

	@Override
	public void deleteUserRole(int id) {
		session.getCurrentSession().delete(getUserRole(id));
	}

	@Override
	public void editUserRole(User_Role user_role) {
		session.getCurrentSession().update(user_role);
	}

	@Override
	public User_Role getUserRole(int id) {
		return (User_Role)session.getCurrentSession().get(User_Role.class, id);
	}

	@Override
	public User_Role userRoleExists(String user_role) {
		return (User_Role) session.getCurrentSession().createQuery("from User_Role as ur where ur.name=?").setString(0, user_role).uniqueResult();
	}

	@Override
	public void deleteAll() {
		session.getCurrentSession().createQuery("delete from User_Role").executeUpdate();
	}
}
