package com.toomanythoughts.tmt.web.layers.logic.auth.model;

import java.util.Set;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

public class UserForRegistrationModel extends EpicPojo {

	private Integer id;
	private Set<Role> roles;
	private PersonalData personalData;
	private Credentials credentials;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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
