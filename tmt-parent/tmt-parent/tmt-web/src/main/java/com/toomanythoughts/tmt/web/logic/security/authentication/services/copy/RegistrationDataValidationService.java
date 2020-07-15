package com.toomanythoughts.tmt.web.logic.security.authentication.services.copy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.toomanythoughts.tmt.web.exceptions.security.EmailAlreadyExistsException;
import com.toomanythoughts.tmt.web.logic.security.authorization.security.UserService;

@Component
public class RegistrationDataValidationService {

	@Autowired
	UserService userService;

	public void validateEmail(final String email) throws EmailAlreadyExistsException {
		if (this.userService.entityByEmail(email) != null) {
			throw EmailAlreadyExistsException.prepare(email);
		}
	}
}
