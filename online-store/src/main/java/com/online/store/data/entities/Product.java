package com.online.store.data.entities;

import java.util.Arrays;
import java.util.Base64;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	private int price;
	
	@Column(name = "main_image")
	@Lob
	private byte[] mainImage;
	
	@OneToMany(
			mappedBy = "product", 
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private Set<ProductImage> images;
	
	@OneToMany(mappedBy = "product")
	private Set<ProductItem> productItems;
	
	/**
	 * Stock is going to be computed when needed. So, this
	 * field must be set in the service layer whenever it 
	 * is necessary.
	 */
	@Transient
	private int stock;
	
	public String getMainImageAsString() {
		 return Base64.getEncoder().encodeToString(this.mainImage);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Set<ProductImage> getImages() {
		return images;
	}

	public void setImages(Set<ProductImage> images) {
		this.images = images;
	}

	public byte[] getMainImage() {
		return mainImage;
	}

	public void setMainImage(byte[] mainImage) {
		this.mainImage = mainImage;
	}

	public Set<ProductItem> getProductItems() {
		return productItems;
	}

	public void setProductItems(Set<ProductItem> productItems) {
		this.productItems = productItems;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this)
			return true;
		
		if(!(o instanceof Product))
			return false;
		
		Product other = (Product) o;
		
		return other.getId() != null 
				&& id.equals(other.id);
	}
	
	@Override
	public int hashCode() {
		return 99;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" 
				+ description + ", price=" + price + ", mainImage=" 
				+ Arrays.toString(mainImage) + ", images=" + images 
				+ ", productItems=" + productItems + ", stock=" + stock + "]";
	}
	
}
