package com.toomanythoughts.tmt.web.layers.logic.auth.services.authorization;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.toomanythoughts.tmt.web.layers.logic.auth.model.authentication.AuthenticationModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.authorization.CredentialsModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.authorization.PersonalDataModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.authorization.UserModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.services.DayOfBirthService;
import com.toomanythoughts.tmt.web.layers.persistence.daos.UserDao;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.UserEntity;

@Component
public class UserService {

	@Autowired
	UserDao userDao;
	@Autowired
	RoleService roleService;
	@Autowired
	DayOfBirthService dayofBirthService;

	public UserEntity entityByUsernameOrEmail(final AuthenticationModel authenticationModel) {
		if (this.usernameIsEmail(authenticationModel)) {
			return this.entityByEmail(authenticationModel.getUsername());
		} else {
			return this.entityByUsername(authenticationModel.getUsername());
		}
	}

	public UserEntity entityByEmail(String email) {
		return this.userDao.byEmail(email);
	}

	public UserEntity entityByUsername(String username) {
		return this.userDao.byUsername(username);
	}

	public UserModel save(final UserEntity entity) {
		return this.toModel(this.userDao.update(entity));
	}

	private UserModel toModel(final UserEntity entity) {
		final UserModel model = new UserModel();
		model.setCredentials(this.credentials(entity));
		model.setEmailValidated(entity.isEmailValidated());
		model.setPaymentValidated(entity.isPaymentValidated());
		model.setPersonalData(this.personalData(entity));
		model.setPostalValidated(entity.isPostalValidated());
		model.setRoles(this.roleService.roles(entity.getRoles()));
		return model;
	}

	private CredentialsModel credentials(UserEntity entity) {
		final CredentialsModel credentials = new CredentialsModel();
		credentials.setAccessToken(entity.getAccessToken());
		credentials.setEmail(entity.getEmail());
		credentials.setRefreshToken(entity.getRefreshToken());
		credentials.setUsername(entity.getUsername());
		return credentials;
	}

	private PersonalDataModel personalData(UserEntity entity) {
		final PersonalDataModel personalData = new PersonalDataModel();
		personalData.setDayOfBirth(this.dayofBirthService.toDayOfBirth(entity.getDayOfBirth()));
		personalData.setFirstName(entity.getFirstName());
		personalData.setLastName(entity.getLastName());
		personalData.setMiddleNames(entity.getMiddleNames());
		personalData.setSex(entity.getSex());
		personalData.setTitle(entity.getTitle());
		return personalData;
	}

	private boolean usernameIsEmail(final AuthenticationModel authenticationModel) {
		try {
			new InternetAddress(authenticationModel.getUsername());
			return true;
		} catch (final AddressException e) {
			return false;
		}
	}
}
