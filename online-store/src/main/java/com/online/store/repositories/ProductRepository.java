package com.online.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.store.data.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
