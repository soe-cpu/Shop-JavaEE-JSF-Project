package com.mmit.shop.bean;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;


import com.mmit.shop.model.service.UserService;
import com.mmit.shop.security.CommonException;

@Named
@RequestScoped
public class ChangePasswordBean {
	
	private String newPassword;
	private String currentPassword;
	private String confirmPassword;
	@Inject
	private UserService service;
	public String changePassword() {
		try {
			service.changePassword(currentPassword,newPassword,confirmPassword);
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			return "/index.xhtml?faces-redirect=true";
		} catch (CommonException e) {
			FacesContext cxt = FacesContext.getCurrentInstance();
			String str = e.getMessage();
			if(str.contains("Incorrect"))
				cxt.addMessage("editForm:currentPass", new FacesMessage(str));
			else if(str.contains("Confirm"))
				cxt.addMessage("editForm:confirmPass", new FacesMessage(str));
		}
		return null;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
	
	
}
