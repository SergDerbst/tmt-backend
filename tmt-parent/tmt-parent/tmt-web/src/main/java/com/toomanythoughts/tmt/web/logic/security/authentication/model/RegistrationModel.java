package com.toomanythoughts.tmt.web.logic.security.authentication.model;

import java.util.Set;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;
import com.toomanythoughts.tmt.web.logic.geo.LanguageModel;
import com.toomanythoughts.tmt.web.logic.security.authorization.model.PersonalDataModel;
import com.toomanythoughts.tmt.web.logic.security.authorization.model.RoleModel;

public class RegistrationModel extends EpicPojo implements LanguageModel {

	private Integer id;
	private PersonalDataModel personalData;
	private RegistrationCredentialsModel credentials;
	private String preferredLanguage;
	private Set<RoleModel> roles;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<RoleModel> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}

	public PersonalDataModel getPersonalData() {
		return this.personalData;
	}

	public void setPersonalData(PersonalDataModel personalData) {
		this.personalData = personalData;
	}

	public RegistrationCredentialsModel getCredentials() {
		return this.credentials;
	}

	public void setCredentials(RegistrationCredentialsModel credentials) {
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
