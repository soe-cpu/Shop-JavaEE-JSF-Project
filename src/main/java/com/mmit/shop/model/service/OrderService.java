package com.mmit.shop.model.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mmit.shop.model.entity.Orders;

@Stateless
public class OrderService {
	@PersistenceContext
	private EntityManager em;


	public List<Orders> findAll() {
		return em.createNamedQuery("Orders.findAll",Orders.class).getResultList();
	}
}
