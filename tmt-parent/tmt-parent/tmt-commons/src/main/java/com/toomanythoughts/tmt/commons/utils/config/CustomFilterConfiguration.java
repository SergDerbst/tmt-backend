package com.toomanythoughts.tmt.commons.utils.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.toomanythoughts.tmt.commons.utils.filters.CustomCorsFilter;

@Configuration
public class CustomFilterConfiguration {

	private static final Integer Cors_Filter_Order = -1;

	@Bean
	public FilterRegistrationBean<CustomCorsFilter> corsFilterRegistration(final CustomCorsFilter customCorsFilter) {
		final FilterRegistrationBean<CustomCorsFilter> filterRegistrationBean = new FilterRegistrationBean<>(customCorsFilter);
		filterRegistrationBean.setOrder(Cors_Filter_Order);
		return filterRegistrationBean;
	}
}
