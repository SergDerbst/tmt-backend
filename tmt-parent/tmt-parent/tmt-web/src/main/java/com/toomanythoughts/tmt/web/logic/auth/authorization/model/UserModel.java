package com.toomanythoughts.tmt.web.logic.auth.authorization.model;

import java.util.Set;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;
import com.toomanythoughts.tmt.web.logic.geo.model.AddressModel;

public class UserModel extends EpicPojo {

	private CredentialsModel credentials;
	private PersonalDataModel personalData;
	private AddressModel<UserModel> address;
	private Set<RoleModel> roles;
	private boolean isEmailValidated;
	private boolean isPaymentValidated;
	private boolean isPostalValidated;

	public CredentialsModel getCredentials() {
		return this.credentials;
	}

	public void setCredentials(CredentialsModel credentials) {
		this.credentials = credentials;
	}

	public PersonalDataModel getPersonalData() {
		return this.personalData;
	}

	public void setPersonalData(PersonalDataModel personalData) {
		this.personalData = personalData;
	}

	public AddressModel<UserModel> getAddress() {
		return this.address;
	}

	public void setAddress(AddressModel<UserModel> address) {
		this.address = address;
	}

	public Set<RoleModel> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<RoleModel> roles) {
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
