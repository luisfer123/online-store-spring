package com.online.store.services.interfaces;

import org.springframework.data.domain.Page;

import com.online.store.data.entities.Product;

public interface IProductService {
	
	void save(Product product);
	
	Product findProductByIdWithImages(Long productId);
	
	Page<Product> findAllPaginated(int requestedPage);

	void delete(Long productId);
	
	Product findByIdWithProductItems(Long productId);
	
	Product findById(Long id);

}
