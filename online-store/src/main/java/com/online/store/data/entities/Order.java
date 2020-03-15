package com.online.store.data.entities;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;
	
	@Column(name = "purchase_date")
	private Timestamp purchaseDate;
	
	@ManyToOne
	@JoinColumn(name = "User_id")
	private User user;
	
	@OneToMany(mappedBy = "order")
	private Set<ProductItem> OrderItem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Timestamp purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Set<ProductItem> getOrderItem() {
		return OrderItem;
	}

	public void setOrderItem(Set<ProductItem> orderItem) {
		OrderItem = orderItem;
	}

	@Override
	public boolean equals(Object o) {
		if(o == this)
			return true;
		
		if(!(o instanceof Order))
			return false;
		
		Order other = (Order) o;
		
		return other.getId() != null
				&& id != null
				&& id.equals(other.id);
	}
	
	@Override
	public int hashCode() {
		return 33;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", purchaseDate=" 
				+ purchaseDate + ", user=" + user 
				+ ", OrderItem=" + OrderItem + "]";
	}
	
}
