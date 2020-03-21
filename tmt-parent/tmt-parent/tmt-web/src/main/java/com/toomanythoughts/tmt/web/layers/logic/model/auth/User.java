package com.toomanythoughts.tmt.web.layers.logic.model.auth;

import java.util.Set;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

public class User extends EpicPojo {

	private Integer userId;
	private Set<Role> roles;
	private PersonalData personalData;
	private Credentials credentials;

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public PersonalData getPersonalData() {
		return this.personalData;
	}

	public void setPersonalData(PersonalData personalData) {
		this.personalData = personalData;
	}

	public Credentials getCredentials() {
		return this.credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
}
