package com.mmit.shop.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.Orders;
import com.mmit.shop.model.service.OrderService;

@Named
@RequestScoped
public class OrderBean {
	private List<Orders> orderList;
	@Inject
	private OrderService orderService;
	
	@PostConstruct
	private void init() {
		orderList = orderService.findAll();
	}

	public List<Orders> getOrderList() {
		return orderList;
	}
	
}
