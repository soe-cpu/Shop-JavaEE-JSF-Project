package com.mmit.shop.bean;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.Users;
import com.mmit.shop.model.service.UserService;



@Named
@ViewScoped
public class UserBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Users  user;
	private List<Users> users;
	@Inject
	private UserService service;
	@Inject
	private FacesContext cxt;
	@PostConstruct
	private void init() {
		users = service.findAll();
		String uId = cxt.getExternalContext().getRequestParameterMap().get("userId");
		if(uId != null)
			user = service.findById(Integer.parseInt(uId));
		else
			user = new Users();
		
		
	}
	
//	public void checkEmail () {
//		Users tmp_obj = service.findByEmail(user.getLoginId());
//		if(tmp_obj != null) {
//			FacesContext cxt = FacesContext.getCurrentInstance();
//			cxt.addMessage("create:loginId", new FacesMessage(tmp_obj.getLoginId()+"already exist"));
//			cxt.validationFailed();
//		}
//	}
	
	public String createUser() {
		try {
			service.creatUser(user);
			return "/admin/users?faces-redirect=true";
		} catch (Exception e) {
			FacesContext cxt = FacesContext.getCurrentInstance();
			cxt.addMessage("editForm:loginId", new FacesMessage("Login Already Exist"));
		}
		return null;
		
	}
	
	public String remove(int id) {
		service.remove(id);
		return "/admin/users.xhtml?faces-redirect=true";
	}
	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public List<Users> getUsers() {
		return users;
	}
	
	

}
