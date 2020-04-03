package com.toomanythoughts.tmt.web.layers.logic.auth.services.authentication;

import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.toomanythoughts.tmt.web.layers.exceptions.auth.EmailValidationFailedException;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.authentication.EmailVerificationModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.services.authorization.RoleService;
import com.toomanythoughts.tmt.web.layers.logic.communication.email.impl.RegistrationEmailContentBuilder;
import com.toomanythoughts.tmt.web.layers.persistence.daos.UserDao;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.UserEntity;

@Component
public class EmailVerificationService {

	@Autowired
	JavaMailSender mailSender;
	@Autowired
	RegistrationService userRegistrationService;
	@Autowired
	RoleService roleService;
	@Autowired
	UserDao userDao;
	@Autowired
	RegistrationEmailContentBuilder mailBuilder;

	public EmailVerificationModel sendHtmlMail(final EmailVerificationModel model) {
		final boolean html = true;
		final MimeMessagePreparator msgPreparator = mimeMessage -> {
			final MimeMessageHelper msgHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
			msgHelper.setFrom("petesideburner@gmail.com");
			msgHelper.setTo(model.getCredentials().getEmail());
			msgHelper.setSubject(this.mailBuilder.subject(model));
			msgHelper.setText(this.mailBuilder.build(model), html);
		};
		this.mailSender.send(msgPreparator);
		return this.verificationMailSent(model);
	}


	public void verifyEmail(final Integer userId,
													final String validationKey) {
		final UserEntity entity = this.userDao.getById(userId);
		if (entity.getEmailValidationKey().equals(validationKey)) {
			entity.setEmailValidated(true);
			entity.setEmailValidationKey(null);
			this.userDao.update(entity);
		} else {
			throw EmailValidationFailedException.prepare(entity.getUsername(), entity.getEmail());
		}
	}

	private EmailVerificationModel verificationMailSent(EmailVerificationModel model) {
		final UserEntity entity = this.userDao.getById(model.getId());
		final Date sent = new Date();
		entity.setEmailValidationSent(sent);
		this.userDao.update(entity);
		model.setEmailSentAt(new DateTime(sent));
		return model;
	}
}
