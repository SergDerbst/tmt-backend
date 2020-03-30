package com.toomanythoughts.tmt.web.layers.config;

import java.util.Locale;
import java.util.Properties;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class RestApiConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
			.addMapping("/**")
			.allowedMethods("HEAD", "DELETE", "GET", "OPTIONS", "PATCH", "POST", "PUT");
	}

	@Bean
	public JavaMailSender mailSender() {
		final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("petesideburner@gmail.com");
		mailSender.setPassword("Dummfrass_666");

		final Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		return mailSender;
	}

	@Bean
	public LocaleResolver localeResolver() {
		final SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	@Bean
	public MessageSource messageSource() {
		final ReloadableResourceBundleMessageSource msgSource = new ReloadableResourceBundleMessageSource();
		msgSource.setBasename("classpath:/i18n/messages");
		msgSource.setDefaultEncoding("UTF-8");
		return msgSource;
	}
}
