package com.toomanythoughts.tmt.web.layers.logic.auth.model.authorization;

import java.util.Set;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

public class RoleModel extends EpicPojo {

	private Integer id;
	private String name;
	private Set<PermissionModel> permissions;

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

	public Set<PermissionModel> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(Set<PermissionModel> permissions) {
		this.permissions = permissions;
	}
}
