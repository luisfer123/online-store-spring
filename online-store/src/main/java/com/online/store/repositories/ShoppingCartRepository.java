package com.online.store.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.store.data.entities.ShoppingCart;
import com.online.store.data.entities.User;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
	
	Optional<ShoppingCart> findByUser(User user);

}
