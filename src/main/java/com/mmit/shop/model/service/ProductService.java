package com.mmit.shop.model.service;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.Part;

import com.mmit.shop.bean.LoginBean;
import com.mmit.shop.model.entity.Brand;
import com.mmit.shop.model.entity.Category;
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

	public List<Product> findProduct() {
		return em.createNamedQuery("Product.findProduct",Product.class).getResultList();
	}

	public void uploadData(Part fileUpload) {
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(fileUpload.getInputStream()));){
			String line = null;
			while( (line=br.readLine() ) != null) {
				createProduct(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private void createProduct(String line) {
		String data[] = line.split("\t");
		if(data.length == 5) {
			Category c = getCategory(data[1]);
			
			Brand b = getBrand(data[2]);
			
			Product p = getProduct(c,b,data[0]);
			
			p.setPrice(Integer.parseInt(data[3]));
			p.setPoductDetails(data[4]);
		}
		
	}

	private Product getProduct(Category c, Brand b, String name) {
		Product p = new Product();
		TypedQuery<Product> query = em.createNamedQuery("Product.findByNameBrandCategory", Product.class);
		query.setParameter("bId", b.getId());
		query.setParameter("cId", c.getId());
		query.setParameter("pname", name);
		try {
			p=query.getSingleResult();
		} catch (Exception e) {
//			e.printStackTrace();
			p=new Product();
			p.setBrand(b);
			p.setCategory(c);
			p.setName(name);
			em.persist(p);
		}
		return p;
	}

	private Brand getBrand(String bname) {
		Brand b =null;
		try {
			b = em.createNamedQuery("Brand.findByName",Brand.class)
					.setParameter("name", bname)
					.getSingleResult();
		} catch (Exception e) {
//			e.printStackTrace();
			b = new Brand();
			b.setName(bname);
			em.persist(b);
		}
		return b;
	}

	private Category getCategory(String cname) {
		TypedQuery<Category> query = em.createNamedQuery("Category.findById", Category.class);
		query.setParameter("name", cname);
		Category cat = null;
		try {
			cat = query.getSingleResult();
		} catch (Exception e) {
//			e.printStackTrace();
			cat = new Category();
			cat.setName(cname);
			em.persist(cat);
		}
		return cat;//managed entity
	}
}
