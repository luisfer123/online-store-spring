package com.online.store.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.online.store.data.entities.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
	
	@Query("select i from ProductImage i join i.product p where p.id = :productId")
	Set<ProductImage> findByProductId(@Param("productId") Long productId);

}
