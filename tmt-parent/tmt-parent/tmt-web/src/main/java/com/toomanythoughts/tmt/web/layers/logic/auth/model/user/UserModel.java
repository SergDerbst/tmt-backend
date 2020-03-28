package com.toomanythoughts.tmt.web.layers.logic.auth.model.user;

import java.util.Set;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

public class UserModel extends EpicPojo {

	private Integer id;
	private UserCredentialsModel credentials;
	private UserPersonalDataModel personalData;
	private UserAddress address;
	private Set<UserRoleModel> roles;
	private boolean isEmailValidated;
	private boolean isPaymentValidated;
	private boolean isPostalValidated;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserCredentialsModel getCredentials() {
		return credentials;
	}

	public void setCredentials(UserCredentialsModel credentials) {
		this.credentials = credentials;
	}

	public UserPersonalDataModel getPersonalData() {
		return this.personalData;
	}

	public void setPersonalData(UserPersonalDataModel personalData) {
		this.personalData = personalData;
	}

	public UserAddress getAddress() {
		return this.address;
	}

	public void setAddress(UserAddress address) {
		this.address = address;
	}

	public Set<UserRoleModel> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<UserRoleModel> roles) {
		this.roles = roles;
	}

	public boolean isEmailValidated() {
		return this.isEmailValidated;
	}

	public void setEmailValidated(boolean isEmailValidated) {
		this.isEmailValidated = isEmailValidated;
	}

	public boolean isPaymentValidated() {
		return this.isPaymentValidated;
	}

	public void setPaymentValidated(boolean isPaymentValidated) {
		this.isPaymentValidated = isPaymentValidated;
	}

	public boolean isPostalValidated() {
		return this.isPostalValidated;
	}

	public void setPostalValidated(boolean isPostalValidated) {
		this.isPostalValidated = isPostalValidated;
	}
}
