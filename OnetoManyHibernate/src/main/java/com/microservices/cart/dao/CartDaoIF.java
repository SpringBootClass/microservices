package com.microservices.cart.dao;

import java.util.List;

import com.microservices.cart.exception.CartException;
import com.microservices.cart.model.Cart;

public interface CartDaoIF {
	public List<Cart> getCarts() throws CartException;
	public Object getCart(Long cartId) throws CartException;
	public Object saveCart(Cart cart) throws CartException;
	public Object updateCart(Cart cart) throws CartException;
	public Object deleteCart(Long cartId) throws CartException;
}
