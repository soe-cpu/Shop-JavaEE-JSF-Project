package com.mmit.shop.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.mmit.shop.model.entity.Product;
import com.mmit.shop.model.service.ProductService;

@Named
@ViewScoped
public class ItemBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Product> items;
	@Inject
	private ProductService service;

	@PostConstruct
	private void init () {
		items = service.findProduct();
	}
	public List<Product> getItems() {
		return items;
	}
	
}
