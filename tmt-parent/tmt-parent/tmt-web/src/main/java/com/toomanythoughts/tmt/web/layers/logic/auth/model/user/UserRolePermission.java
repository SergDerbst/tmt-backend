package com.toomanythoughts.tmt.web.layers.logic.auth.model.user;

import java.util.Map;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

public class UserRolePermission extends EpicPojo {

	private Integer id;
	private String name;
	private Map<String, Object> configuration;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Object> getConfiguration() {
		return this.configuration;
	}

	public void setConfiguration(Map<String, Object> configuration) {
		this.configuration = configuration;
	}
}
