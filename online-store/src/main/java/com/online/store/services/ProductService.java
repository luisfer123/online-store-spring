package com.online.store.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.store.data.entities.Product;
import com.online.store.data.entities.ProductImage;
import com.online.store.repositories.ProductImageRepository;
import com.online.store.repositories.ProductRepository;
import com.online.store.services.interfaces.IProductService;

@Service
@Transactional
public class ProductService implements IProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductImageRepository productImageRepository;

	@Override
	@Transactional(readOnly = false)
	public void save(Product product) {
		productRepository.save(product);
	}
	
	@Override
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
	
	@Override
	@Transactional(readOnly = true)
	public Page<Product> findAllPaginated(int requestedPage) {
		
		PageRequest pageRequest =
				PageRequest.of(requestedPage, 1, Sort.by("name").descending());
		
		return productRepository.findAll(pageRequest);
	}

}
