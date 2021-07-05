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
public class MemberBean {

	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	
	@Inject
	private UserService  service;
//	public String changePassword() {
//		try {
//			service.changePassword(oldPassword,newPassword,confirmPassword);
//			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//		} catch (CommonException e) {
//			FacesContext cxt = FacesContext.getCurrentInstance();
//			cxt.addMessage(null, new FacesMessage(e.getMessage()));
//			return null;
//		}
//		return "views/member/member-home.xhtml?faces-redirect=true";
//	}
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	

}
