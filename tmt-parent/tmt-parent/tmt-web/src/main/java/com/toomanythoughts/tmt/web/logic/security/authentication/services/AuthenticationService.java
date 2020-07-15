package com.toomanythoughts.tmt.web.logic.security.authentication.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.toomanythoughts.tmt.web.exceptions.security.AuthorizationFailedException;
import com.toomanythoughts.tmt.web.logic.security.SecurityConstants;
import com.toomanythoughts.tmt.web.logic.security.authentication.model.AuthenticatedModel;
import com.toomanythoughts.tmt.web.logic.security.authentication.model.AuthenticationModel;
import com.toomanythoughts.tmt.web.logic.security.authorization.security.UserService;
import com.toomanythoughts.tmt.web.persistence.entities.security.UserEntity;

@Component
public class AuthenticationService {

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserService userService;

	public AuthenticatedModel authenticate(final AuthenticationModel authenticationModel) throws AuthorizationFailedException {
		final UserEntity entity = this.userService.entityByUsernameOrEmail(authenticationModel);

		if (entity == null || !this.passwordEncoder.matches(authenticationModel.getPassword(), entity.getPassword())) {
			throw AuthorizationFailedException.prepare(authenticationModel.getUsername());
		}

		final AuthenticatedModel authenticatedModel = new AuthenticatedModel();
		authenticatedModel.setUsername(authenticationModel.getUsername());
		authenticatedModel.setToken(this.token(authenticationModel));
		return authenticatedModel;
	}

	private String token(AuthenticationModel authenticationModel) {
		return SecurityConstants.Token_Prefix + JWT.create()
			.withSubject(authenticationModel.getUsername())
			.withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.Expiration_Time))
			.sign(Algorithm.HMAC512(SecurityConstants.Secret.getBytes()));
	}
}
