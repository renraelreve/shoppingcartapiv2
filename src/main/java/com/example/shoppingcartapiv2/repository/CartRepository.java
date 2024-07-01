package com.example.shoppingcartapiv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shoppingcartapiv2.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
