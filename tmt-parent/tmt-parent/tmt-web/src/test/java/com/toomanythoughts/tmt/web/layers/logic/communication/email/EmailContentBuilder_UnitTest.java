package com.toomanythoughts.tmt.web.layers.logic.communication.email;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.toomanythoughts.tmt.web.logic.auth.authentication.model.EmailVerificationModel;
import com.toomanythoughts.tmt.web.logic.communications.email.EmailContentBuilder;

@RunWith(SpringRunner.class)
public class EmailContentBuilder_UnitTest {

	private static final String _EmailContent = "eat this!";
	private static final String _TemplateName = "registrationMailTemplate";
	private static final String _PreferredLanguageCode = "en-US";
	private static final String _SubjectMessageKey = "registration.mail.subject";

	@TestConfiguration
	static class EmailContentBuilderUnitTestConfiguration {
		@Bean
		public EmailContentBuilder<EmailVerificationModel> toTest() {
			return new EmailContentBuilder<>();
		}
		@Bean
		public MessageSource messageSource() {
			return new MessageSource() {

				@Override
				public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
					throw new RuntimeException("get out of here!");
				}

				@Override
				public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
					assertThat(code, equalTo(_SubjectMessageKey));
					assertThat(args, nullValue());
					assertThat(locale.getCountry(), equalTo("US"));
					assertThat(locale.getLanguage(), equalTo("en"));
					return "whatever message";
				}

				@Override
				public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
					throw new RuntimeException("get out of here!");
				}
			};
		}
	}

	@Autowired
	EmailContentBuilder<EmailVerificationModel> toTest;
	@MockBean
	TemplateEngine templateEngine;
	@MockBean
	EmailVerificationModel emailVerificationModel;

	@Test
	public void build() {
		//given
		when(this.emailVerificationModel.getPreferredLanguage()).thenReturn(_PreferredLanguageCode);
		when(this.templateEngine.process(eq(_TemplateName), any(Context.class))).thenReturn(_EmailContent);
		//when
		final String text = this.toTest.build(this.emailVerificationModel, _TemplateName);
		//then
		assertThat(text, equalTo(_EmailContent));
		verify(this.templateEngine, times(1)).process(eq(_TemplateName), argThat(context(new Locale("en", "US"), "user", this.emailVerificationModel)));
	}

	@Test
	public void subject() {
		//given
		when(this.emailVerificationModel.getPreferredLanguage()).thenReturn(_PreferredLanguageCode);
		//when
		final String subject = this.toTest.subject(this.emailVerificationModel, _SubjectMessageKey);
		//then
		assertThat(subject, equalTo("whatever message"));
	}

	private static ContextMatcher context(final Locale locale, final String variableKey, final Object variableValue) {
		return new ContextMatcher(locale, variableKey, variableValue);
	}

	private static class ContextMatcher implements ArgumentMatcher<Context> {
		private final Locale locale;
		private final String variableKey;
		private final Object variableValue;

		public ContextMatcher(final Locale locale, final String variableKey, final Object variableValue) {
			this.locale = locale;
			this.variableKey = variableKey;
			this.variableValue = variableValue;
		}

		@Override
		public boolean matches(Context context) {
			return this.locale.getCountry().equals(context.getLocale().getCountry()) &&
						 this.locale.getLanguage().equals(context.getLocale().getLanguage()) &&
						 this.variableValue.equals(context.getVariable(this.variableKey));
		}
	}
}
