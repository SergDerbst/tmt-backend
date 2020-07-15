package com.toomanythoughts.tmt.web.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toomanythoughts.tmt.web.exceptions.security.UsernameAlreadyExistsException;
import com.toomanythoughts.tmt.web.logic.security.authorization.security.UserService;

@Service
public class DataValidationService {

	@Autowired
	UserService userService;

	public void uniqueUsername(final String username) throws UsernameAlreadyExistsException {
		if (this.userService.entityByUsername(username) != null) {
			throw UsernameAlreadyExistsException.prepare(username);
		}
	}
}
