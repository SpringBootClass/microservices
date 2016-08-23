package com.microservices.cart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.cart.model.Cart;
import com.microservices.cart.service.CartServiceIF;
import com.microservices.result.Result;

@RestController
@RequestMapping(path = "/cart/v1")
public class CartController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CartServiceIF cartServiceIF;

	public CartServiceIF getCartServiceIF() {
		return cartServiceIF;
	}

	public void setCartServiceIF(CartServiceIF cartServiceIF) {
		this.cartServiceIF = cartServiceIF;
	}

	@RequestMapping(value = "/carts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getCarts() {

		HttpStatus statusCode = null;
		Result result = new Result();
		List<Cart> castList = null;
		try {
			castList = cartServiceIF.getCarts();
			if (castList.isEmpty()) {
				result.setObject(castList);
			} else {
				result.setObject(castList);
				statusCode = HttpStatus.OK;
			}

		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(castList);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getCart(@PathVariable("cartId") String cartId) {

		HttpStatus statusCode = null;
		Result result = new Result();
		Object cart = null;
		try {
			cart = cartServiceIF.getCart(Long.parseLong(cartId));
			if (cart == null) {
				result.setObject(cart);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				result.setObject(cart);
				statusCode = HttpStatus.OK;
			}

		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(cart);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}
		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> saveCart(@RequestBody Cart cart) {

		HttpStatus statusCode = null;
		Result result = new Result();
		try {
			if (cart == null) {
				result.setObject(cart);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				logger.info("cart.getId() else: "+cart.getId());
				cartServiceIF.saveCart(cart);
				result.setObject(cart);
				statusCode = HttpStatus.OK;
			}
		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(cart);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}
		return new ResponseEntity<Result>(result, statusCode);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> updateCart(@RequestBody Cart cart) {

		HttpStatus statusCode = null;
		Result result = new Result();
		Object cart1 = null;
		try {
			if (cart == null) {
				result.setObject(cart);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				cart1 = cartServiceIF.updateCart(cart);
				if(cart1 == null){
					result.setObject(cart1);
					statusCode = HttpStatus.NO_CONTENT;
				}
				else{
				result.setObject(cart1);
				statusCode = HttpStatus.OK;
				}
			}
		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(cart);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> deleteCart(@PathVariable("cartId") String cartId) {
		HttpStatus statusCode = null;
		Result result = new Result();
		Object cart = null;
		try {
			cart = cartServiceIF.deleteCart(Long.parseLong(cartId));
			if (cart == null) {
				result.setObject(cart);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				result.setObject(cart);
				statusCode = HttpStatus.OK;
			}

		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(cart);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

}
