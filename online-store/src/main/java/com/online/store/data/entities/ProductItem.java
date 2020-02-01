package com.online.store.data.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.online.store.data.enums.ProductItemStatus;

@Entity
@Table(name = "Product_items")
public class ProductItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;
	
	@Column(name = "production_date")
	private Date productionDate;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private ProductItemStatus status;
	
}
