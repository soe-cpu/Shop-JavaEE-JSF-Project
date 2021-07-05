package com.mmit.shop.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.Users;
import com.mmit.shop.model.entity.Users.Role;
import com.mmit.shop.model.service.UserService;

@Named
@ViewScoped
public class RegisterBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Users user;
	@Inject
	private UserService service;
	@PostConstruct
	private void init() {
		user = new Users();
		user.setRole(Role.Customer);
	}
	public String register() {
		try {
			service.creatUser(user);
			return "/index.xhtml?faces-redirect=true";
		} catch (Exception e) {
			FacesContext cxt = FacesContext.getCurrentInstance();
			cxt.addMessage("editForm:loginId", new FacesMessage("Login Already Exist"));
		}
		return null;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	
	
}
