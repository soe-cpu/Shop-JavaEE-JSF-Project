package com.mmit.shop.model.service;

import java.security.Principal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import com.mmit.shop.model.entity.Product;
import com.mmit.shop.model.entity.Users;
import com.mmit.shop.security.CommonException;




@Stateless
public class UserService {

	@Inject
	private Pbkdf2PasswordHash encoder;
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	Principal principal;
	
	public void creatUser(Users user) {
		if(user.getId()==0) {
			user.setPassword(encoder.generate(user.getPassword().toCharArray()));
			em.persist(user);
		}
		else {
			em.merge(user);
		}
		
	}


	public List<Users> findAll() {
		TypedQuery<Users> query = em.createNamedQuery("Users.findAll", Users.class);
		query.setParameter("loginId", principal.getName());//return loginId(email)
		
		return query.getResultList();
	}
	
	public Users findById(int id) {
			
			return em.find(Users.class, id);
		}

	public long getCount() {
		TypedQuery<Long> query = em.createNamedQuery("Users.getCount", Long.class);
		return query.getSingleResult();
	}


	public Users findByLoginId(String caller) {
		
		try {
			return em.createNamedQuery("Users.findByLoginId",Users.class)
					.setParameter("loginId", caller)
					.getSingleResult();
			
		} catch (NoResultException e) {
			
		}
		return null;
	}


	public void changePassword(String currentPassword, String newPassword, String confirmPassword) {
		Users user = findByLoginId(principal.getName());
		if(!encoder.verify(currentPassword.toCharArray(), user.getPassword())) {
			//not match old password
			throw new CommonException("Incorrect current password!");
		}
		if(!newPassword.equals(confirmPassword)) {
			throw new CommonException("Confirm password do not match");
		}
		user.setPassword(encoder.generate(newPassword.toCharArray()));
		
	}


	public void remove(int id) {
		Users u = em.find(Users.class, id);
		em.remove(u);
		
	}





}
