package com.toomanythoughts.tmt.web.layers.logic.auth.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toomanythoughts.tmt.web.layers.logic.auth.model.user.UserCredentialsModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.user.UserForEmailValidationModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.user.UserForRegistrationModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.user.UserPersonalDataModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.user.UserPersonalDataModel.DayOfBirth;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.user.UserRoleModel;
import com.toomanythoughts.tmt.web.layers.persistence.daos.UserDao;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.RoleEntity;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.UserEntity;

/**
 * This services registers new users. It transforms the {@link UserForRegistrationModel}
 * to a {@link UserEntity}, creates it in the database, and transforms the resulting entity
 * to a {@link UserForEmailValidationModel}, which will be returned to the application.
 *
 * @author Sergio Weigel
 *
 */
@Service
public class UserRegistrationService {

	@Autowired
	UserDao userDao;
	@Autowired
	RoleService roleService;
	@Autowired
	UserEmailValidationService emailService;

	public UserForEmailValidationModel create(UserForRegistrationModel user) {
		return this.emailService.sendMail(this.toModel(this.userDao.create(this.toEntity(user))));
	}

	public UserEntity toEntity(final UserForRegistrationModel model) {
		final UserEntity entity = new UserEntity();
		entity.setDayOfBirth(this.toDate(model.getPersonalData().getDayOfBirth()));
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
			for (final UserRoleModel role : model.getRoles()) {
				roles.add(this.roleService.toEntity(role));
			}
		}
		entity.setRoles(roles);
		entity.setSex(model.getPersonalData().getSex());
		entity.setTitle(model.getPersonalData().getTitle());
		entity.setUsername(model.getCredentials().getUsername());
		return entity;
	}

	public UserForEmailValidationModel toModel(final UserEntity entity) {
		final UserForEmailValidationModel model = new UserForEmailValidationModel();
		final UserPersonalDataModel personalData = this.personalData(entity);
		final UserCredentialsModel credentials = this.userCredentials(entity);
		model.setId(entity.getId());
		model.setEmailValidated(entity.isEmailValidated());
		model.setEmailValidationKey(entity.getEmailValidationKey());
		model.setCredentials(credentials);
		model.setPersonalData(personalData);
		model.setPreferredLanguage(entity.getPreferredLanguage());
		final Set<UserRoleModel> roles = new HashSet<>();
		if (entity.getRoles() != null) {
			for (final RoleEntity role : entity.getRoles()) {
				roles.add(this.roleService.toModel(role));
			}
		}
		model.setRoles(roles);
		return model;
	}

	public UserCredentialsModel userCredentials(UserEntity entity) {
		final UserCredentialsModel credentials = new UserCredentialsModel();
		credentials.setEmail(entity.getEmail());
		credentials.setUsername(entity.getUsername());
		return credentials;
	}

	public UserPersonalDataModel personalData(UserEntity entity) {
		final UserPersonalDataModel personalData = new UserPersonalDataModel();
		personalData.setDayOfBirth(this.toDayOfBirth(entity.getDayOfBirth()));
		personalData.setFirstName(entity.getFirstName());
		personalData.setLastName(entity.getLastName());
		personalData.setMiddleNames(entity.getMiddleNames());
		personalData.setSex(entity.getSex());
		personalData.setTitle(entity.getTitle());
		return personalData;
	}

	public DayOfBirth toDayOfBirth(final Date date) {
		final DayOfBirth dayOfBirth = new DayOfBirth();
		final LocalDate localDate = new java.sql.Date(date.getTime()).toLocalDate();
		dayOfBirth.setDay(localDate.getDayOfMonth());
		dayOfBirth.setMonth(localDate.getMonthValue());
		dayOfBirth.setYear(localDate.getYear());
		return dayOfBirth;
	}

	public Date toDate(DayOfBirth dayOfBirth) {
		return java.sql.Date.valueOf(LocalDate.of(dayOfBirth.getYear(), dayOfBirth.getMonth(), dayOfBirth.getDay()));
	}
}
