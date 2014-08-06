package com.sanjay31321.sys.preset.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanjay31321.sys.model.User_Role;
import com.sanjay31321.sys.service.UserRoleService;

@Service
public class InsertUserRole {
	private static final Logger logger = LoggerFactory.getLogger(InsertUserRole.class);
	
	@Autowired private UserRoleService userRoleService;
	
	public void insert() {
		User_Role role = new User_Role();
		
		role.setId(1);
		role.setName("ROLE_ADMIN");
		logger.info("id : "+ role.getId() + " | Role : " + role.getName());
		userRoleService.addUserRole(role);
		
		role.setId(2);
		role.setName("ROLE_TEACHER");
		userRoleService.addUserRole(role);
		
		role.setId(3);
		role.setName("ROLE_STUDENT");
		userRoleService.addUserRole(role);
	}
}
