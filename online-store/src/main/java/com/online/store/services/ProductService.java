package com.online.store.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.store.data.entities.Product;
import com.online.store.data.entities.ProductImage;
import com.online.store.repositories.ProductImageRepository;
import com.online.store.repositories.ProductRepository;

@Service
@Transactional
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductImageRepository productImageRepository;

	public void save(Product product) {
		productRepository.save(product);
	}
	
	@Transactional(readOnly = true)
	public Product findProductByIdWithImages(Long productId) {
		Optional<Product> optional = productRepository.findById(productId);
		if(!optional.isPresent())
			throw new RuntimeException();
		
		Product product = optional.get();
		
		Set<ProductImage> images = productImageRepository.findByProductId(productId);
		product.setImages(images);
		
		return product;
	}

}
