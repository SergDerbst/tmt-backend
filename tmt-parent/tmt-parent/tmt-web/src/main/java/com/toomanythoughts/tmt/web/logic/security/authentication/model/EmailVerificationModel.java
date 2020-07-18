package com.toomanythoughts.tmt.web.logic.security.authentication.model;

import java.time.LocalDateTime;
import java.util.Set;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;
import com.toomanythoughts.tmt.web.logic.geo.LanguageModel;
import com.toomanythoughts.tmt.web.logic.security.authorization.model.CredentialsModel;
import com.toomanythoughts.tmt.web.logic.security.authorization.model.PersonalDataModel;
import com.toomanythoughts.tmt.web.logic.security.authorization.model.RoleModel;

public class EmailVerificationModel extends EpicPojo implements LanguageModel {

	private Integer id;
	private Set<RoleModel> roles;
	private PersonalDataModel personalData;
	private CredentialsModel credentials;
	private String preferredLanguage;
	private String emailValidationKey;

	private boolean isEmailValidated;
	private LocalDateTime emailSentAt;
	private String encryptedMail;

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Set<RoleModel> getRoles() {
		return this.roles;
	}

	public void setRoles(final Set<RoleModel> roles) {
		this.roles = roles;
	}

	public PersonalDataModel getPersonalData() {
		return this.personalData;
	}

	public void setPersonalData(final PersonalDataModel personalData) {
		this.personalData = personalData;
	}

	public boolean isEmailValidated() {
		return this.isEmailValidated;
	}

	public void setEmailValidated(final boolean isEmailValidated) {
		this.isEmailValidated = isEmailValidated;
	}

	public CredentialsModel getCredentials() {
		return this.credentials;
	}

	public void setCredentials(CredentialsModel credentials) {
		this.credentials = credentials;
	}

	public LocalDateTime getEmailSentAt() {
		return this.emailSentAt;
	}

	public void setEmailSentAt(LocalDateTime emailSentAt) {
		this.emailSentAt = emailSentAt;
	}

	@Override
	public String getPreferredLanguage() {
		return this.preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public String getEncryptedMail() {
		return this.encryptedMail;
	}

	public void setEncryptedMail(String encryptedMail) {
		this.encryptedMail = encryptedMail;
	}

	public String getEmailValidationKey() {
		return this.emailValidationKey;
	}

	public void setEmailValidationKey(String emailValidationKey) {
		this.emailValidationKey = emailValidationKey;
	}
}
