package com.microservices.item.controller;

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

import com.microservices.item.model.Item;
import com.microservices.item.service.ItemServiceIF;
import com.microservices.result.Result;

@RestController
@RequestMapping(path = "/item/v1")
public class ItemController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ItemServiceIF itemServiceIF;

	public ItemServiceIF getItemServiceIF() {
		return itemServiceIF;
	}

	public void setItemServiceIF(ItemServiceIF itemServiceIF) {
		this.itemServiceIF = itemServiceIF;
	}

	@RequestMapping(value = "/items", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getItems() {

		HttpStatus statusCode = null;
		Result result = new Result();
		List<Item> itemList = null;
		try {
			itemList = itemServiceIF.getItems();
			if (itemList.isEmpty()) {
				result.setObject(itemList);
			} else {
				result.setObject(itemList);
				statusCode = HttpStatus.OK;
			}

		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(itemList);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/{itemId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getItem(@PathVariable("itemId") String itemId) {

		HttpStatus statusCode = null;
		Result result = new Result();
		Object item = null;
		try {
			item = itemServiceIF.getItem(Long.parseLong(itemId));
			if (item == null) {
				result.setObject(item);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				result.setObject(item);
				statusCode = HttpStatus.OK;
			}
		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(item);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> saveItem(@RequestBody Item item) {

		HttpStatus statusCode = null;
		Result result = new Result();
		try {
			if (item == null) {
				result.setObject(item);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				logger.info("item.getId() else: " + item.getId());
				itemServiceIF.saveItem(item);
				result.setObject(item);
				statusCode = HttpStatus.OK;
			}
		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(item);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}
		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> updateCart(@RequestBody Item item) {

		HttpStatus statusCode = null;
		Result result = new Result();
		Object item1 = null;
		try {
			if (item == null) {
				result.setObject(item);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				item1 = itemServiceIF.updateItem(item);
				if (item1 == null) {
					result.setObject(item1);
					statusCode = HttpStatus.NO_CONTENT;
				} else {
					result.setObject(item1);
					statusCode = HttpStatus.OK;
				}
			}
		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(item);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> deleteCart(@PathVariable("itemId") String itemId) {
		HttpStatus statusCode = null;
		Result result = new Result();
		Object item = null;
		try {
			item = itemServiceIF.deleteItem(Long.parseLong(itemId));
			if (item == null) {
				result.setObject(item);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				result.setObject(item);
				statusCode = HttpStatus.OK;
			}

		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(item);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

}
