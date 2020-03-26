package com.toomanythoughts.tmt.web.layers.logic.auth.model;

import java.util.Set;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

public class UserForEmailValidationModel extends EpicPojo {

	private Integer id;
	private Set<Role> roles;
	private PersonalData personalData;
	private boolean isEmailValidated;

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(final Set<Role> roles) {
		this.roles = roles;
	}

	public PersonalData getPersonalData() {
		return this.personalData;
	}

	public void setPersonalData(final PersonalData personalData) {
		this.personalData = personalData;
	}

	public boolean isEmailValidated() {
		return this.isEmailValidated;
	}

	public void setEmailValidated(final boolean isEmailValidated) {
		this.isEmailValidated = isEmailValidated;
	}
}
