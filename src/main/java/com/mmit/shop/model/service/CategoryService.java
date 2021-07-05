package com.mmit.shop.model.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.mmit.shop.model.entity.Category;

@Stateless
public class CategoryService {
	@PersistenceContext
	private EntityManager em;

	public void save(Category category) {
		if(category.getId() == 0)
			em.persist(category);
		else
			em.merge(category);
		
	}

	public List<Category> findAll() {
		TypedQuery<Category> qery = em.createNamedQuery("Category.findAll", Category.class);
		return qery.getResultList();
	}

	public Category findById(int id) {
		
		return em.find(Category.class, id);
	}
}
