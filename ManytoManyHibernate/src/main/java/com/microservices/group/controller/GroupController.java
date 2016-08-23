package com.microservices.group.controller;

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

import com.microservices.group.model.Group;
import com.microservices.group.service.GroupServiceIF;
import com.microservices.result.Result;

@RestController
@RequestMapping(path = "/group/v1")
public class GroupController {

	
	
	@Autowired
	private GroupServiceIF groupServiceIF;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/groups", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getGroups() {

		HttpStatus statusCode = null;
		Result result = new Result();
		List<Group> groupList = null;
		try {
			groupList = groupServiceIF.getGroups();
			if (groupList.isEmpty()) {
				result.setObject(groupList);
			} else {
				result.setObject(groupList);
				statusCode = HttpStatus.OK;
			}

		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(groupList);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/{groupId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getGroup(@PathVariable("groupId") String groupId) {

		HttpStatus statusCode = null;
		Result result = new Result();
		Object employeeAddress = null;
		try {
			employeeAddress = groupServiceIF.getGroup(Long.parseLong(groupId));
			if (employeeAddress == null) {
				result.setObject(employeeAddress);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				result.setObject(employeeAddress);
				statusCode = HttpStatus.OK;
			}

		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(employeeAddress);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	public GroupServiceIF getGroupServiceIF() {
		return groupServiceIF;
	}

	public void setGroupServiceIF(GroupServiceIF groupServiceIF) {
		this.groupServiceIF = groupServiceIF;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> saveGroup(@RequestBody Group group) {

		HttpStatus statusCode = null;
		Result result = new Result();
		try {
			if (group == null) {
				result.setObject(group);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				logger.info("group.getId() else: "+group.getId());
				groupServiceIF.saveGroup(group);
				result.setObject(group);
				statusCode = HttpStatus.OK;
			}
		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(group);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}
		return new ResponseEntity<Result>(result, statusCode);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> updateGroup(@RequestBody Group group) {

		HttpStatus statusCode = null;
		Result result = new Result();
		Object group1 = null;
		try {
			if (group == null) {
				result.setObject(group);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				group1 = groupServiceIF.updateGroup(group);
				if(group1 == null){
					result.setObject(group1);
					statusCode = HttpStatus.NO_CONTENT;
				}
				else{
				result.setObject(group1);
				statusCode = HttpStatus.OK;
				}
			}
		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(group);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/{groupId}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> deleteGroup(@PathVariable("groupId") String groupId) {
		HttpStatus statusCode = null;
		Result result = new Result();
		Object group = null;
		try {
			group = groupServiceIF.deleteGroup(Long.parseLong(groupId));
			if (group == null) {
				result.setObject(group);
				statusCode = HttpStatus.NO_CONTENT;
			} else {
				result.setObject(group);
				statusCode = HttpStatus.OK;
			}

		} catch (Exception e) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(group);
			result.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	
}
