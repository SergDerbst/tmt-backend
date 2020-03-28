package com.toomanythoughts.tmt.web.layers.logic.auth.model.user;

import java.util.Set;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;
import com.toomanythoughts.tmt.web.layers.logic.LanguageModel;

public class UserForRegistrationModel extends EpicPojo implements LanguageModel {

	private Integer id;
	private UserPersonalDataModel personalData;
	private UserCredentialsForRegistrationModel credentials;
	private String preferredLanguage;
	private Set<UserRoleModel> roles;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<UserRoleModel> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<UserRoleModel> roles) {
		this.roles = roles;
	}

	public UserPersonalDataModel getPersonalData() {
		return this.personalData;
	}

	public void setPersonalData(UserPersonalDataModel personalData) {
		this.personalData = personalData;
	}

	public UserCredentialsForRegistrationModel getCredentials() {
		return this.credentials;
	}

	public void setCredentials(UserCredentialsForRegistrationModel credentials) {
		this.credentials = credentials;
	}

	@Override
	public String getPreferredLanguage() {
		return this.preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}
}
