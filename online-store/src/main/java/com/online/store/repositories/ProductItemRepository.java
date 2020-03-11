package com.online.store.repositories;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.online.store.data.entities.ProductItem;

public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {

	@Query("select i from ProductItem i join i.product p where p.id = :productId")
	Set<ProductItem> findByProductId(@Param("productId") Long productId);
	
	@Query("select count(i.id) from ProductItem i "
			+ "where i.product.id = :productId and i.status = 'STOCK'")
	int getStock(@Param("productId") Long productId);
	
	@Query("select i from ProductItem i join i.product p where p.id = :productId and i.status = 'STOCK'")
	Page<ProductItem> findAllPaginatedForProductId(@Param("productId") Long productId, Pageable requestedPage);
}
