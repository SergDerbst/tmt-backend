package com.toomanythoughts.tmt.web.layers.logic.communication.email;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.toomanythoughts.tmt.web.layers.logic.LanguageModel;

@Component
public class EmailContentBuilder<MODEL extends LanguageModel> {

	@Autowired
	TemplateEngine templateEngine;
	@Autowired
	MessageSource messageSource;

	public String build(final MODEL model, final String templateName) {
		return this.templateEngine.process(templateName, this.resolveMessageContext(model));
	}

	public String subject(final MODEL model, final String messageKey) {
		return this.messageSource.getMessage(messageKey, null, this.locale(model));
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
}
