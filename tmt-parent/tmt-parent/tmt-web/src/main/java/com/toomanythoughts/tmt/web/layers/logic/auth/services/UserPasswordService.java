package com.toomanythoughts.tmt.web.layers.logic.auth.services;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.springframework.stereotype.Component;

@Component
public class UserPasswordService extends DefaultPasswordService {

	public String encryptPassword(final String password) {
		return super.encryptPassword(password);
	}
}
