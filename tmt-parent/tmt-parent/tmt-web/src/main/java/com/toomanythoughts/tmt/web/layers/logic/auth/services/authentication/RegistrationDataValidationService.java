package com.toomanythoughts.tmt.web.layers.logic.auth.services.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.toomanythoughts.tmt.web.layers.exceptions.auth.EmailAlreadyExistsException;
import com.toomanythoughts.tmt.web.layers.exceptions.auth.UsernameAlreadyExistsException;
import com.toomanythoughts.tmt.web.layers.logic.auth.services.authorization.UserService;

@Component
public class RegistrationDataValidationService {

	@Autowired
	UserService userService;

	public void validateEmail(final String email) throws EmailAlreadyExistsException {
		if (this.userService.entityByEmail(email) != null) {
			throw EmailAlreadyExistsException.prepare(email);
		}
	}

	public void validateUsername(final String username) throws UsernameAlreadyExistsException {
		if (this.userService.entityByUsername(username) != null) {
			throw UsernameAlreadyExistsException.prepare(username);
		}
	}
}
