package com.mmit.shop.model.service;



import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mmit.shop.bean.LoginBean;
import com.mmit.shop.model.entity.Product;

@Stateless
public class ProductService {
	
	@PersistenceContext
	private EntityManager em;
	@Inject
	private LoginBean loginBean;
	public List<Product> findAll() {
		
		return em.createNamedQuery("Product.findAll",Product.class).getResultList();
	}

	public Product findById(int id) {
		
		return em.find(Product.class, id);
	}

	public void save(Product product) {
		if(product.getId() == 0)
		{
			product.setCreated_by(loginBean.getLoginUser());
			em.persist(product);
		}
		else {
//			Product p = findById(product.getId());
//			product.setCreated_by(p.getCreated_by());
//			product.setCreated_at(p.getCreated_at());
			product.setUpdated_by(loginBean.getLoginUser());
			em.merge(product);
		}
	}

	public void remove(int id) {
		Product p = em.find(Product.class, id);
		em.remove(p);
		
	}

	public String findPhotoById(int id) {
		return em.createNamedQuery("Product.findPhotoById",String.class)
				.setParameter("pId", id)
				.getSingleResult();

	}
}
