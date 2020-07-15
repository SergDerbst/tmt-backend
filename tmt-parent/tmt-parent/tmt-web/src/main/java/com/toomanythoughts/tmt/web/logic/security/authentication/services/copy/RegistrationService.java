package com.toomanythoughts.tmt.web.logic.security.authentication.services.copy;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toomanythoughts.tmt.web.logic.security.DayOfBirthService;
import com.toomanythoughts.tmt.web.logic.security.authentication.model.EmailVerificationModel;
import com.toomanythoughts.tmt.web.logic.security.authentication.model.RegistrationModel;
import com.toomanythoughts.tmt.web.logic.security.authorization.model.CredentialsModel;
import com.toomanythoughts.tmt.web.logic.security.authorization.model.PersonalDataModel;
import com.toomanythoughts.tmt.web.logic.security.authorization.model.RoleModel;
import com.toomanythoughts.tmt.web.logic.security.authorization.security.RoleService;
import com.toomanythoughts.tmt.web.persistence.daos.security.UserDao;
import com.toomanythoughts.tmt.web.persistence.entities.security.RoleEntity;
import com.toomanythoughts.tmt.web.persistence.entities.security.UserEntity;

/**
 * This services registers new users. It transforms the {@link RegistrationModel}
 * to a {@link UserEntity}, creates it in the database, and transforms the resulting entity
 * to a {@link EmailVerificationModel}, which will be returned to the application.
 *
 * @author Sergio Weigel
 *
 */
@Service
public class RegistrationService {

	@Autowired
	UserDao userDao;
	@Autowired
	RoleService roleService;
	@Autowired
	EmailVerificationService emailService;
	@Autowired
	DayOfBirthService dayOfBirthService;

	public EmailVerificationModel create(RegistrationModel user) {
		return this.emailService.sendHtmlMail(this.toModel(this.userDao.create(this.toEntity(user))));
	}

	public UserEntity toEntity(final RegistrationModel model) {
		final UserEntity entity = new UserEntity();
		entity.setDayOfBirth(this.dayOfBirthService.toDate(model.getPersonalData().getDayOfBirth()));
		entity.setEmail(model.getCredentials().getEmail());
		entity.setEmailValidationKey(String.valueOf(100000000 + new Random().nextInt(900000000)));
		entity.setFirstName(model.getPersonalData().getFirstName());
		entity.setId(model.getId());
		entity.setLastName(model.getPersonalData().getLastName());
		entity.setMiddleNames(model.getPersonalData().getMiddleNames());
		entity.setPassword(model.getCredentials().getPassword());
		entity.setPreferredLanguage(model.getPreferredLanguage());
		final Set<RoleEntity> roles = new HashSet<>();
		if(model.getRoles() != null) {
			for (final RoleModel role : model.getRoles()) {
				roles.add(this.roleService.toEntity(role));
			}
		}
		entity.setRoles(roles);
		entity.setSex(model.getPersonalData().getSex());
		entity.setTitle(model.getPersonalData().getTitle());
		entity.setUsername(model.getCredentials().getUsername());
		return entity;
	}

	public EmailVerificationModel toModel(final UserEntity entity) {
		final EmailVerificationModel model = new EmailVerificationModel();
		final PersonalDataModel personalData = this.personalData(entity);
		final CredentialsModel credentials = this.userCredentials(entity);
		model.setId(entity.getId());
		model.setEmailValidated(entity.isEmailValidated());
		model.setEmailValidationKey(entity.getEmailValidationKey());
		model.setCredentials(credentials);
		model.setPersonalData(personalData);
		model.setPreferredLanguage(entity.getPreferredLanguage());
		final Set<RoleModel> roles = new HashSet<>();
		if (entity.getRoles() != null) {
			for (final RoleEntity role : entity.getRoles()) {
				roles.add(this.roleService.toModel(role));
			}
		}
		model.setRoles(roles);
		return model;
	}

	public CredentialsModel userCredentials(final UserEntity entity) {
		final CredentialsModel credentials = new CredentialsModel();
		credentials.setEmail(entity.getEmail());
		credentials.setUsername(entity.getUsername());
		return credentials;
	}

	public PersonalDataModel personalData(final UserEntity entity) {
		final PersonalDataModel personalData = new PersonalDataModel();
		personalData.setDayOfBirth(this.dayOfBirthService.toDayOfBirth(entity.getDayOfBirth()));
		personalData.setFirstName(entity.getFirstName());
		personalData.setLastName(entity.getLastName());
		personalData.setMiddleNames(entity.getMiddleNames());
		personalData.setSex(entity.getSex());
		personalData.setTitle(entity.getTitle());
		return personalData;
	}
}
