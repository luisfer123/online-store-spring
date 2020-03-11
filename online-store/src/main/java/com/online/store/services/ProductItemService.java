package com.online.store.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.store.data.entities.Product;
import com.online.store.data.entities.ProductItem;
import com.online.store.data.enums.ProductItemStatus;
import com.online.store.exceptions.ProductNotFoundException;
import com.online.store.repositories.ProductItemRepository;
import com.online.store.repositories.ProductRepository;

@Service
@Transactional
public class ProductItemService {
	
	@Autowired
	private ProductItemRepository itemRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Transactional(readOnly = true)
	public Page<ProductItem> findAllPaginatedForProductId(
			Long productId, int pageNumber, int pageSize, String sortBy) {
		
		Pageable requestedPage = 
				PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		
		Page<ProductItem> page = 
				itemRepo.findAllPaginatedForProductId(productId, requestedPage);
		
		return page;
	}
	
	@Transactional(readOnly = true)
	public int getStockForProductId(Long productId) {
		return itemRepo.getStock(productId);
	}
	
	@Transactional
	public ProductItem save(ProductItem newItem, Long productId) {
		
		Optional<Product> oProduct = productRepo.findById(productId);
		if(!oProduct.isPresent())
			throw new ProductNotFoundException();
		
		newItem.setProduct(oProduct.get());
		newItem.setStatus(ProductItemStatus.STOCK);
		
		ProductItem item = itemRepo.save(newItem);
		
		return item;
	}

}
