package com.toomanythoughts.tmt.web.layers.logic.auth.model.user;

import java.util.Set;

import org.joda.time.DateTime;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;
import com.toomanythoughts.tmt.web.layers.logic.LanguageModel;

public class UserForEmailValidationModel extends EpicPojo implements LanguageModel {

	private Integer id;
	private Set<UserRoleModel> roles;
	private UserPersonalDataModel personalData;
	private UserCredentialsModel credentials;
	private String preferredLanguage;
	private String emailValidationKey;

	private boolean isEmailValidated;
	private DateTime emailSentAt;
	private String encryptedMail;

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Set<UserRoleModel> getRoles() {
		return this.roles;
	}

	public void setRoles(final Set<UserRoleModel> roles) {
		this.roles = roles;
	}

	public UserPersonalDataModel getPersonalData() {
		return this.personalData;
	}

	public void setPersonalData(final UserPersonalDataModel personalData) {
		this.personalData = personalData;
	}

	public boolean isEmailValidated() {
		return this.isEmailValidated;
	}

	public void setEmailValidated(final boolean isEmailValidated) {
		this.isEmailValidated = isEmailValidated;
	}

	public UserCredentialsModel getCredentials() {
		return this.credentials;
	}

	public void setCredentials(UserCredentialsModel credentials) {
		this.credentials = credentials;
	}

	public DateTime getEmailSentAt() {
		return this.emailSentAt;
	}

	public void setEmailSentAt(DateTime emailSentAt) {
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
