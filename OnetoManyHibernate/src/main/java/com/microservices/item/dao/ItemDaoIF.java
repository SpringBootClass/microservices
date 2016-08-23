package com.microservices.item.dao;

import java.util.List;

import com.microservices.item.exception.ItemException;
import com.microservices.item.model.Item;

public interface ItemDaoIF {
	public List<Item> getItems() throws ItemException;
	public Object getItem(Long itemId) throws ItemException;
	public Object saveItem(Item item) throws ItemException;
	public Object updateItem(Item item) throws ItemException;
	public Object deleteItem(Long itemId) throws ItemException;	
}
