package com.microservices.group.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.group.dao.GroupDaoIF;
import com.microservices.group.exception.GroupException;
import com.microservices.group.model.Group;

@Service
public class GroupServiceImpl implements GroupServiceIF {
	
	@Autowired
	private GroupDaoIF groupDaoIF;

	@Override
	public List<Group> getGroups() throws GroupException {
		return groupDaoIF.getGroups();
	}

	@Override
	public Object getGroup(Long groupId) throws GroupException {
		return groupDaoIF.getGroup(groupId);
	}

	public GroupDaoIF getGroupDaoIF() {
		return groupDaoIF;
	}

	public void setGroupDaoIF(GroupDaoIF groupDaoIF) {
		this.groupDaoIF = groupDaoIF;
	}

	@Override
	public Object saveGroup(Group group) throws GroupException {
		return groupDaoIF.saveGroup(group);
	}

	@Override
	public Object updateGroup(Group group) throws GroupException {
		return groupDaoIF.updateGroup(group);
	}

	@Override
	public Object deleteGroup(Long groupId) throws GroupException {
		return groupDaoIF.deleteGroup(groupId);
	}
	
	
}
