package com.microservices.group.dao;

import java.util.List;

import com.microservices.group.exception.GroupException;
import com.microservices.group.model.Group;

public interface GroupDaoIF {
	public List<Group> getGroups() throws GroupException;
	public Object getGroup(Long groupId) throws GroupException;
	public Object saveGroup(Group group) throws GroupException;
	public Object updateGroup(Group group) throws GroupException;
	public Object deleteGroup(Long groupId) throws GroupException;
}
