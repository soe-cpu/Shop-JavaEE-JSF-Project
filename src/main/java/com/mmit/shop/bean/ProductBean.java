package com.mmit.shop.bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import com.mmit.shop.model.entity.Product;
import com.mmit.shop.model.service.ProductService;


@Named
@ViewScoped
public class ProductBean implements Serializable{


	private static final long serialVersionUID = 1L;
	private List<Product> products;
	@Inject
	private ProductService service;
	
	private Product product;
	
	private Part imgPart;
	private Part fileUpload;
	
	private ServletContext cxt; 
	private String imgFolder = null;
	@PostConstruct
	private void init() {
		products=service.findAll();
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pId");
		if(id != null && !id.equals(""))
			product = service.findById(Integer.parseInt(id));
		else
			product=new Product();
		cxt = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		imgFolder =  cxt.getRealPath("resources/uploads");
	}
	public String save() {
		
		try {
			if(imgPart != null) {			
				String photoName = getPhotoName(imgPart.getSubmittedFileName());
				//edit product(delete old photo)
				if(product.getId() != 0 ) {
					String oldPhoto = service.findPhotoById(product.getId());
					if(oldPhoto != null) {
						File oldFile = new File(imgFolder+File.separator+oldPhoto);
						if(oldFile.exists())
							oldFile.delete();
					}
				}
				
				imgPart.write(imgFolder+File.separator+photoName);//save
				product.setPhoto(photoName);
			}
			else {//image part is null edit product
				
				if(product.getId() != 0)
					product.setPhoto(service.findPhotoById(product.getId()));
			}
			
			service.save(product);
			return "/admin/products.xhtml?faces-redirect=true";
		} catch (EJBException e) {
			FacesContext cxt = FacesContext.getCurrentInstance();
			cxt.addMessage("editForm:name", new FacesMessage("Product Name already exist!"));
		}
		catch (IOException e) {
			
		}
		
		return null;
	}
	private String getPhotoName(String submitName) {
		//submittedFileName='user.jpg';
		String tmpName = submitName.substring(0,submitName.lastIndexOf("."));
		String newName = "img"+System.currentTimeMillis();
		submitName = submitName.replace(tmpName, newName);
		return submitName;
	}
	public String remove(int id) {
		String photoName = service.findPhotoById(id);
		if(photoName != null) {//remove photo
			File photo = new File(imgFolder+File.separator+photoName);
			if(photo.exists())
				photo.delete();
		}
		service.remove(id);
		return "/admin/products.xhtml?faces-redirect=true";
	}
	public String upload() {
		if(fileUpload != null && fileUpload.getSubmittedFileName().equals("")) {
			service.uploadData(fileUpload);
		}
		return null;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Part getImgPart() {
		return imgPart;
	}
	public void setImgPart(Part imgPart) {
		this.imgPart = imgPart;
	}
	public Part getFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(Part fileUpload) {
		this.fileUpload = fileUpload;
	}
	
	
}
