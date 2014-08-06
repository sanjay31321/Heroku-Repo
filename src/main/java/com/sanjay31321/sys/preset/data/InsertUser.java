package com.sanjay31321.sys.preset.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanjay31321.sys.model.User;
import com.sanjay31321.sys.service.UserRoleService;
import com.sanjay31321.sys.service.UserService;

@Service
public class InsertUser {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	public void insert() {
		User user = new User();
		
		user.setId(1);
		user.setEmail("admin@admin.com");
		user.setPassword("admin");
		user.setName("Admin");
		user.setUser_role(userRoleService.getUserRole(1));
		user.setSecurity_question("My secret passcode ?");
		user.setSecurity_answer("9999");
		user.setActive(true);
		userService.Register(user);
		
		user.setId(2);
		user.setEmail("samuel@gmail.com");
		user.setPassword("password");
		user.setName("Samuel Kidane");
		user.setUser_role(userRoleService.getUserRole(2));
		user.setSecurity_question("My secret passcode ?");
		user.setSecurity_answer("8888");
		user.setActive(true);
		userService.Register(user);
		
		user.setId(3);
		user.setEmail("adam@gmail.com");
		user.setPassword("password");
		user.setName("Adam Smith");
		user.setUser_role(userRoleService.getUserRole(2));
		user.setSecurity_question("My secret passcode ?");
		user.setSecurity_answer("8888");
		user.setActive(true);
		userService.Register(user);
		
			
		
		user.setId(4);
		user.setEmail("nakul@gmail.com");
		user.setPassword("password");
		user.setName("Nakul Bhoomkar");
		user.setUser_role(userRoleService.getUserRole(3));
		user.setSecurity_question("My secret passcode ?");
		user.setSecurity_answer("0000");
		user.setActive(true);
		userService.Register(user);
		
		user.setId(5);
		user.setEmail("sanjay@gmail.com");
		user.setPassword("password");
		user.setName("Sanjay Kumar");
		user.setUser_role(userRoleService.getUserRole(3));
		user.setSecurity_question("My secret passcode ?");
		user.setSecurity_answer("0000");
		user.setActive(true);
		userService.Register(user);
		
		user.setId(6);
		user.setEmail("smith@gmail.com");
		user.setPassword("password");
		user.setName("Smit");
		user.setUser_role(userRoleService.getUserRole(3));
		user.setSecurity_question("My secret passcode ?");
		user.setSecurity_answer("0000");
		user.setActive(true);
		userService.Register(user);
		
		user.setId(7);
		user.setEmail("han@gmail.com");
		user.setPassword("password");
		user.setName("Xia Han");
		user.setUser_role(userRoleService.getUserRole(3));
		user.setSecurity_question("My secret passcode ?");
		user.setSecurity_answer("0000");
		user.setActive(true);
		userService.Register(user);
		
		user.setId(8);
		user.setEmail("sam@gmail.com");
		user.setPassword("password");
		user.setName("Sam Jones");
		user.setUser_role(userRoleService.getUserRole(3));
		user.setSecurity_question("My secret passcode ?");
		user.setSecurity_answer("0000");
		user.setActive(true);
		userService.Register(user);
	}

}
