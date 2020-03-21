package com.toomanythoughts.tmt.web.layers.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.toomanythoughts.tmt.web.layers.logic.model.auth.TMTRealm;

@Configuration
public class RestApiConfig {

	@Bean
	public Realm realm() {
		return new TMTRealm();
	}

	@Bean
	public ShiroFilterChainDefinition shiroFilterChainDefinition() {
		final DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
		chainDefinition.addPathDefinition("/**", "anon");
		return chainDefinition;
	}
}
