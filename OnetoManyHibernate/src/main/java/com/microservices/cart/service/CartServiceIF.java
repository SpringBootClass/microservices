package com.microservices.cart.service;

import java.util.List;

import com.microservices.cart.exception.CartException;
import com.microservices.cart.model.Cart;

public interface CartServiceIF {
	public List<Cart> getCarts() throws CartException;
	public Object getCart(Long cardId) throws CartException;
	public Object saveCart(Cart cart) throws CartException;
	public Object updateCart(Cart cart) throws CartException;
	public Object deleteCart(Long cartId) throws CartException;
}
