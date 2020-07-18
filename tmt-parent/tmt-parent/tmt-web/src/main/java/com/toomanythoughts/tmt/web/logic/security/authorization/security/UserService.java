package com.toomanythoughts.tmt.web.logic.security.authorization.security;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.toomanythoughts.tmt.web.logic.security.DayOfBirthService;
import com.toomanythoughts.tmt.web.logic.security.authentication.model.AuthenticationModel;
import com.toomanythoughts.tmt.web.logic.security.authorization.model.CredentialsModel;
import com.toomanythoughts.tmt.web.logic.security.authorization.model.PersonalDataModel;
import com.toomanythoughts.tmt.web.logic.security.authorization.model.SimpleUserModel;
import com.toomanythoughts.tmt.web.logic.security.authorization.model.UserModel;
import com.toomanythoughts.tmt.web.persistence.daos.security.UserDao;
import com.toomanythoughts.tmt.web.persistence.entities.security.UserEntity;

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

	public UserEntity entityById(final Integer id) {
		return this.userDao.getById(id);
	}

	public UserEntity entityByUsername(String username) {
		return this.userDao.getByUsername(username);
	}

	public UserEntity entityByEmail(String email) {
		return this.userDao.byEmail(email);
	}

	public UserModel save(final UserEntity entity) {
		return this.toModel(this.userDao.update(entity));
	}

	public SimpleUserModel toSimpleModel(final UserEntity entity) {
		final SimpleUserModel model = new SimpleUserModel();
		model.setId(entity.getId());
		model.setUsername(entity.getUsername());
		return model;
	}

	public UserModel toModel(final UserEntity entity) {
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
		credentials.setEmail(entity.getEmail());
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
