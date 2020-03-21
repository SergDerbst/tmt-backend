package com.toomanythoughts.tmt.web.layers.logic.model.auth;

import java.util.Set;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

public class Role extends EpicPojo {

	private Integer id;
	private String name;
	private Set<Permission> permissions;

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

	public Set<Permission> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
}
