package com.toomanythoughts.tmt.web.logic.security.authentication.services;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.toomanythoughts.tmt.web.exceptions.security.EmailValidationFailedRuntimeException;
import com.toomanythoughts.tmt.web.logic.communications.email.EmailContentBuilder;
import com.toomanythoughts.tmt.web.logic.security.authentication.model.EmailVerificationModel;
import com.toomanythoughts.tmt.web.logic.security.authorization.security.RoleService;
import com.toomanythoughts.tmt.web.persistence.daos.security.UserDao;
import com.toomanythoughts.tmt.web.persistence.entities.security.UserEntity;

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
	EmailContentBuilder<EmailVerificationModel> mailBuilder;

	public EmailVerificationModel sendHtmlMail(final EmailVerificationModel model) {
		final boolean html = true;
		final MimeMessagePreparator msgPreparator = mimeMessage -> {
			final MimeMessageHelper msgHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
			msgHelper.setFrom("petesideburner@gmail.com");
			msgHelper.setTo(model.getCredentials().getEmail());
			msgHelper.setSubject(this.mailBuilder.subject(model, "registration.mail.subject"));
			msgHelper.setText(this.mailBuilder.build(model, "registrationMailTemplate"), html);
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
			throw EmailValidationFailedRuntimeException.prepare(entity.getUsername(), entity.getEmail());
		}
	}

	private EmailVerificationModel verificationMailSent(EmailVerificationModel model) {
		final UserEntity entity = this.userDao.getById(model.getId());
		final Date sent = new Date();
		entity.setEmailValidationSent(sent);
		this.userDao.update(entity);
		model.setEmailSentAt(new Timestamp(sent.getTime()).toLocalDateTime());
		return model;
	}
}
