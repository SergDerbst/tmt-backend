package com.toomanythoughts.tmt.web.layers.logic.auth.services.authentication;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.test.context.junit4.SpringRunner;

import com.toomanythoughts.tmt.web.exceptions.security.EmailValidationFailedRuntimeException;
import com.toomanythoughts.tmt.web.logic.communications.email.EmailContentBuilder;
import com.toomanythoughts.tmt.web.logic.security.authentication.model.EmailVerificationModel;
import com.toomanythoughts.tmt.web.logic.security.authentication.services.EmailVerificationService;
import com.toomanythoughts.tmt.web.logic.security.authentication.services.RegistrationService;
import com.toomanythoughts.tmt.web.logic.security.authorization.model.CredentialsModel;
import com.toomanythoughts.tmt.web.logic.security.authorization.security.RoleService;
import com.toomanythoughts.tmt.web.persistence.daos.security.UserDao;
import com.toomanythoughts.tmt.web.persistence.entities.security.UserEntity;

@RunWith(SpringRunner.class)
public class EmailVerificationService_UnitTest {

	private static final String _Validation_Key = "20010911";
	private static final String _Message_Text = "ze message";
	private static final String _Subject = "verify this, bitch";
	private static final int _Id = 666;
	private static final String _Email = "email@galore.arsch";

	@TestConfiguration
	static class EmailVerificationServiceUnitTestConfiguration {

		@Bean
		public EmailVerificationService toTest() {
			return new EmailVerificationService();
		}
	}

	@Autowired
	EmailVerificationService toTest;
	@MockBean
	JavaMailSender mailSender;
	@MockBean
	RegistrationService userRegistrationService;
	@MockBean
	RoleService roleService;
	@MockBean
	UserDao userDao;
	@MockBean
	EmailContentBuilder<EmailVerificationModel> mailBuilder;
	@MockBean
	EmailVerificationModel emailVerificationModel;
	@MockBean
	CredentialsModel credentialsModel;
	@MockBean
	UserEntity userEntity;
	@MockBean
	MimeMessage mimeMessage;

	@Captor
	private ArgumentCaptor<MimeMessagePreparator> msgPrepperCaptor;

	@Before
	public void setUp() {
		when(this.emailVerificationModel.getCredentials()).thenReturn(this.credentialsModel);
		when(this.credentialsModel.getEmail()).thenReturn(_Email);
		when(this.emailVerificationModel.getId()).thenReturn(_Id);
		when(this.userDao.getById(eq(_Id))).thenReturn(this.userEntity);
	}

	@Test
	public void sendHtmlMail() throws Exception {
		//given
		when(this.mailBuilder.subject(this.emailVerificationModel, "registration.mail.subject")).thenReturn(_Subject);
		when(this.mailBuilder.build(this.emailVerificationModel, "registrationMailTemplate")).thenReturn(_Message_Text);
		//when
		this.toTest.sendHtmlMail(this.emailVerificationModel);
		//then
		verify(this.userEntity, times(1)).setEmailValidationSent(argThat(new SentDateMatcher()));
		verify(this.userDao, times(1)).update(this.userEntity);
		verify(this.emailVerificationModel, times(1)).setEmailSentAt(argThat(new SentDateTimeMatcher()));
		this.verifyMessagePreparation();
	}

	@Test
	public void verifyEmail_Successful() {
		//given
		when(this.userEntity.getEmailValidationKey()).thenReturn(_Validation_Key);
		//when
		this.toTest.verifyEmail(_Id, _Validation_Key);
		//then
		verify(this.userEntity, times(1)).setEmailValidated(eq(true));
		verify(this.userEntity, times(1)).setEmailValidationKey(isNull());
		verify(this.userDao, times(1)).update(this.userEntity);
	}

	@Test(expected = EmailValidationFailedRuntimeException.class)
	public void verifyEmail_Failure() {
		//given
		when(this.userEntity.getEmailValidationKey()).thenReturn("total rubbish");
		//when
		this.toTest.verifyEmail(_Id, _Validation_Key);
	}

	private void verifyMessagePreparation() throws Exception {
		verify(this.mailSender, times(1)).send(this.msgPrepperCaptor.capture());
		this.msgPrepperCaptor.getValue().prepare(this.mimeMessage);
		verify(this.mimeMessage, times(1)).setFrom(new InternetAddress("petesideburner@gmail.com"));
		verify(this.mimeMessage, times(1)).setRecipient(eq(Message.RecipientType.TO), eq(new InternetAddress(_Email)));
		verify(this.mimeMessage, times(1)).setSubject(_Subject, "UTF-8");
		verify(this.mimeMessage, times(1)).setContent(_Message_Text, "text/html;charset=UTF-8");
	}

	private static class SentDateMatcher implements ArgumentMatcher<Date> {

		@Override
		public boolean matches(final Date sentDate) {
			return this.withinSameMinute(new LocalDateTime(sentDate), new LocalDateTime());
		}

		private boolean withinSameMinute(final LocalDateTime sent, final LocalDateTime now) {
			return sent.getYear() == now.getYear() &&
						 sent.getMonthOfYear() == now.getMonthOfYear() &&
						 sent.getDayOfMonth() == sent.getDayOfMonth() &&
						 sent.getHourOfDay() == sent.getHourOfDay() &&
						 now.getMinuteOfHour() - sent.getMinuteOfHour() <= 1;
		}
	}

	private static class SentDateTimeMatcher implements ArgumentMatcher<DateTime> {

		@Override
		public boolean matches(final DateTime sentDate) {
			return this.withinSameMinute(new LocalDateTime(sentDate), new LocalDateTime());
		}

		private boolean withinSameMinute(final LocalDateTime sent, final LocalDateTime now) {
			return sent.getYear() == now.getYear() &&
						 sent.getMonthOfYear() == now.getMonthOfYear() &&
						 sent.getDayOfMonth() == sent.getDayOfMonth() &&
						 sent.getHourOfDay() == sent.getHourOfDay() &&
						 now.getMinuteOfHour() - sent.getMinuteOfHour() <= 1;
		}
	}
}
