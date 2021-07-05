package com.mmit.shop.security;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.mmit.shop.model.entity.Users;
import com.mmit.shop.model.entity.Users.Role;
import com.mmit.shop.model.service.UserService;



@ApplicationScoped
@Singleton
@Startup
public class AdminUserCreation {
	
	@Inject
	private UserService service;
	@PostConstruct
	private void init() {
		
		long userCount=service.getCount();
		if(userCount == 0) {
			Users user = new Users();
			user.setLoginId("admin@gmail.com");
			user.setPassword("11111111");
			user.setRole(Role.Admin);
			user.setUserName("admin");
			
			service.creatUser(user);
		}
		
	}
}
