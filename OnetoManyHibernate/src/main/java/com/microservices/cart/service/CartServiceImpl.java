package com.microservices.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.cart.dao.CartDaoIF;
import com.microservices.cart.exception.CartException;
import com.microservices.cart.model.Cart;

@Service
public class CartServiceImpl implements CartServiceIF {
	@Autowired
	private CartDaoIF cartDaoIF;
	
	@Override
	public List<Cart> getCarts() throws CartException {
		return cartDaoIF.getCarts();
	}

	@Override
	public Object getCart(Long cartId) throws CartException {
		return cartDaoIF.getCart(cartId);
	}

	public CartDaoIF getCartDaoIF() {
		return cartDaoIF;
	}

	public void setCartDaoIF(CartDaoIF cartDaoIF) {
		this.cartDaoIF = cartDaoIF;
	}

	@Override
	public Object saveCart(Cart cart) throws CartException {
		return cartDaoIF.saveCart(cart);
	}

	@Override
	public Object updateCart(Cart cart) throws CartException {
		return cartDaoIF.updateCart(cart);
	}

	@Override
	public Object deleteCart(Long cartId) throws CartException {
		return cartDaoIF.deleteCart(cartId);
	}

	

}
