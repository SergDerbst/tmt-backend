package com.toomanythoughts.tmt.web.layers.logic.auth.services;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.toomanythoughts.tmt.commons.exceptions.logic.impl.auth.EmailValidationFailedRuntimeException;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.user.UserForEmailValidationModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.user.UserModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.user.UserRoleModel;
import com.toomanythoughts.tmt.web.layers.logic.communication.email.impl.RegistrationEmailContentBuilder;
import com.toomanythoughts.tmt.web.layers.persistence.daos.UserDao;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.RoleEntity;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.UserEntity;

@Component
public class UserEmailValidationService {

	@Autowired
	JavaMailSender mailSender;
	@Autowired
	UserRegistrationService userRegistrationService;
	@Autowired
	RoleService roleService;
	@Autowired
	UserDao userDao;
	@Autowired
	RegistrationEmailContentBuilder mailBuilder;

	public UserForEmailValidationModel sendMail(final UserForEmailValidationModel model) {
		final MimeMessagePreparator msgPreparator = mimeMessage -> {
			final MimeMessageHelper msgHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
			msgHelper.setFrom("petesideburner@gmail.com");
			msgHelper.setTo(model.getCredentials().getEmail());
			msgHelper.setSubject(this.mailBuilder.subject(model));
			msgHelper.setText(this.mailBuilder.build(model));
		};
		this.mailSender.send(msgPreparator);
		return this.verificationMailSent(model);
	}


	public UserModel validate(final Integer userId, final String validationKey) {
		final UserEntity entity = this.userDao.getById(userId);
		if (entity.getEmailValidationKey().equals(validationKey)) {
			entity.setEmailValidated(true);
			entity.setEmailValidationKey(null);
			return this.toModel(this.userDao.update(entity));
		} else {
			throw EmailValidationFailedRuntimeException.prepare(entity.getUsername(), entity.getEmail());
		}
	}

	private UserForEmailValidationModel verificationMailSent(UserForEmailValidationModel model) {
		final UserEntity entity = this.userDao.getById(model.getId());
		final Date sent = new Date();
		entity.setEmailValidationSent(sent);
		this.userDao.update(entity);
		model.setEmailSentAt(new DateTime(sent));
		return model;
	}

	private UserModel toModel(final UserEntity entity) {
		final UserModel model = new UserModel();
		model.setCredentials(this.userRegistrationService.userCredentials(entity));
		model.setEmailValidated(entity.isEmailValidated());
		model.setId(entity.getId());
		model.setPaymentValidated(entity.isPaymentValidated());
		model.setPersonalData(this.userRegistrationService.personalData(entity));
		model.setPostalValidated(entity.isPostalValidated());
		final Set<UserRoleModel> roles = new HashSet<>();
		for (final RoleEntity roleEntity : entity.getRoles()) {
			roles.add(this.roleService.toModel(roleEntity));
		}
		model.setRoles(roles);
		return model;
	}
}
