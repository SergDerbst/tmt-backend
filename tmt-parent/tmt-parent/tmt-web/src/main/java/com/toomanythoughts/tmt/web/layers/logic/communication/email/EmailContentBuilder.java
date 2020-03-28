package com.toomanythoughts.tmt.web.layers.logic.communication.email;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.toomanythoughts.tmt.web.layers.logic.LanguageModel;

public abstract class EmailContentBuilder<MODEL extends LanguageModel> {

	private static final String SUBJECT = "subject";

	@Autowired
	TemplateEngine templateEngine;
	@Autowired
	MessageSource messageSource;

	protected abstract String templateName();

	protected abstract String messageKeyPrefix();

	public String build(final MODEL model) {
		return this.templateEngine.process(this.templateName(), this.resolveMessageContext(model));
	}

	public String subject(final MODEL model) {
		return this.messageSource.getMessage(this.messageKey(SUBJECT), null, this.locale(model));
	}

	protected Context resolveMessageContext(MODEL model) {
		final Context context = new Context();
		context.setLocale(this.locale(model));
		context.setVariable("user", model);
		return context;
	}

	protected Locale locale(final MODEL model) {
		final String[] token = model.getPreferredLanguage().split("\\-");
		final Locale locale = new Locale(token[0], token[1]);
		return locale;
	}

	protected String messageKey(final String key) {
		return this.messageKeyPrefix() + "." + key;
	}
}
