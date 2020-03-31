package com.toomanythoughts.tmt.web.layers.logic.auth.services.authentication;

import java.security.SecureRandom;
import java.util.Base64;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.toomanythoughts.tmt.web.layers.exceptions.auth.AuthenticationFailedException;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.authentication.AuthenticatedModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.authentication.AuthenticationModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.services.authorization.UserService;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.UserEntity;

@Component
public class AuthenticationService {

	private static final SecureRandom secureRandom = new SecureRandom();
	private static final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserService userService;

	public AuthenticatedModel authenticate(final AuthenticationModel authenticationModel) {
		return this.authenticate(authenticationModel, this.userService.entityByUsernameOrEmail(authenticationModel));
	}

	private AuthenticatedModel authenticate(final AuthenticationModel authenticationModel, final UserEntity entity) {
		if (this.passwordEncoder.matches(authenticationModel.getPassword(), entity.getPassword())) {
			entity.setAccessToken(this.random());
			entity.setRefreshToken(this.random());
			entity.setAccessTokenExpiration(new LocalDateTime().plusHours(12).toDate());
			return this.authenticated(authenticationModel, entity);
		} else {
			throw AuthenticationFailedException.prepare();
		}
	}

	private AuthenticatedModel authenticated(final AuthenticationModel authenticationModel, final UserEntity entity) {
		final AuthenticatedModel authenticated = new AuthenticatedModel();
		authenticated.setUser(this.userService.save(entity));
		authenticated.setRedirectTo(authenticated.getRedirectTo());
		return authenticated;
	}

	private String random() {
		final byte[] buffer = new byte[23];
		secureRandom.nextBytes(buffer);
		return encoder.encodeToString(buffer);
	}
}
