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
import com.online.store.exceptions.ProductNotFoundException;
import com.online.store.repositories.ProductImageRepository;
import com.online.store.repositories.ProductItemRepository;
import com.online.store.repositories.ProductRepository;
import com.online.store.services.interfaces.IProductService;

@Service
@Transactional
public class ProductService implements IProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductImageRepository productImageRepository;
	
	@Autowired
	private ProductItemService productItemService;
	
	@Autowired
	private ProductItemRepository productItemRepo;

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
		
		product.setStock(productItemService.getStockForProductId(product.getId()));
		
		return product;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Product> findAllPaginated(int requestedPage) {
		
		int pageSize = 9;
		
		PageRequest pageRequest =
				PageRequest.of(requestedPage, pageSize, Sort.by("name").descending());
		
		return productRepository.findAll(pageRequest);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		Optional<Product> optional = productRepository.findById(id);
		
		if(!optional.isPresent())
			throw new ProductNotFoundException();
		
		productRepository.delete(optional.get());
	}

	public Product findByIdWithProductItems(Long productId) {
		
		Optional<Product> oProduct = productRepository.findById(productId);
		if(!oProduct.isPresent())
			throw new ProductNotFoundException();
		
		Product product = oProduct.get();
		product.setProductItems(
				productItemRepo.findByProductId(productId));
		
		product.setStock(productItemService.getStockForProductId(productId));
		
		return product;
	}
	
	@Transactional(readOnly = true)
	public int productStock(Product product) {
		return productItemService.getStockForProductId(product.getId());
	}
	
	@Override
	public Product findById(Long id) {
		Optional<Product> oProduct = 
				productRepository.findById(id);
		
		if(!oProduct.isPresent())
			throw new ProductNotFoundException();
		
		return oProduct.get();
	}

}
