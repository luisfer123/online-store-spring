package com.online.store.data.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import com.online.store.data.enums.ProductItemStatus;

@Entity
@Table(name = "Product_items",
		uniqueConstraints = {@UniqueConstraint(columnNames = "product_item_id")})
public class ProductItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;
	
	@NotBlank
	@Column(name = "product_item_id")
	private String ProductItemId;
	
	@Column(name = "production_date")
	private Date productionDate;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private ProductItemStatus status;
	
	@ManyToOne
	@JoinColumn(name = "products_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "Orders_id")
	private Order order;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public ProductItemStatus getStatus() {
		return status;
	}

	public void setStatus(ProductItemStatus status) {
		this.status = status;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getProductItemId() {
		return ProductItemId;
	}

	public void setProductItemId(String productItemId) {
		ProductItemId = productItemId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this)
			return true;
		
		if(!(o instanceof ProductItem))
			return false;
		
		ProductItem other = (ProductItem) o;
		
		return other.getId() != null 
				&& id.equals(other.id);
	}
	
	@Override
	public int hashCode() {
		return 333;
	}

	@Override
	public String toString() {
		return "ProductItem [id=" + id + ", ProductItemId=" + ProductItemId 
				+ ", productionDate=" + productionDate + ", location=" 
				+ location + ", status=" + status + ", product=" + product 
				+ ", order=" + order + "]";
	}
	
}
