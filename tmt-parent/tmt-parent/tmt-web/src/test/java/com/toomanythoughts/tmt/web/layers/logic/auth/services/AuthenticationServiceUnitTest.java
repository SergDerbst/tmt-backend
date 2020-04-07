package com.toomanythoughts.tmt.web.layers.logic.auth.services;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.toomanythoughts.tmt.web.layers.exceptions.auth.AuthenticationFailedException;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.authentication.AuthenticatedModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.authentication.AuthenticationModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.authorization.UserModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.services.authentication.AuthenticationService;
import com.toomanythoughts.tmt.web.layers.logic.auth.services.authorization.UserService;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.UserEntity;

@RunWith(SpringRunner.class)
public class AuthenticationServiceUnitTest {

	private static final String _Password = "password_galore";
	private static final String _Password_Kaputt = "password_kaputt";
	private static final String _Redirect = "redirect_galore";

	@TestConfiguration
	static class AuthenticationServiceUnitTestConfiguration {
		@Bean
		public AuthenticationService toTest() {
			return new AuthenticationService();
		}
	}

	@Autowired
	private AuthenticationService toTest;
	@MockBean
	private PasswordEncoder passwordEncoder;
	@MockBean
	private UserService userService;
	@MockBean
	private AuthenticationModel authenticationModel;
	@MockBean
	private UserEntity userEntity;
	@MockBean
	private UserModel userModel;

	@Before
	public void setUp() {
		when(this.userService.entityByUsernameOrEmail(this.authenticationModel)).thenReturn(this.userEntity);
		when(this.userService.save(this.userEntity)).thenReturn(this.userModel);
	}

	@Test
	public void authenticate_PasswordsMatching() {
		//given
		when(this.authenticationModel.getRedirectTo()).thenReturn(_Redirect);
		when(this.authenticationModel.getPassword()).thenReturn(_Password);
		when(this.userEntity.getPassword()).thenReturn(_Password);
		when(this.passwordEncoder.matches(_Password, _Password)).thenReturn(true);
		//when
		final AuthenticatedModel authenticated = this.toTest.authenticate(this.authenticationModel);
		//then
		assertThat(authenticated.getUser(), equalTo(this.userModel));
		assertThat(authenticated.getRedirectTo(), equalTo(_Redirect));
		verify(this.userEntity, times(1)).setAccessToken(argThat(new TokenMatcher()));
		verify(this.userEntity, times(1)).setRefreshToken(argThat(new TokenMatcher()));
		verify(this.userEntity, times(1)).setAccessTokenExpiration(any(Date.class));
	}

	@Test(expected=AuthenticationFailedException.class)
	public void authenticate_PasswordsNotMatching() {
		//given
		when(this.authenticationModel.getRedirectTo()).thenReturn(_Redirect);
		when(this.authenticationModel.getPassword()).thenReturn(_Password_Kaputt);
		when(this.userEntity.getPassword()).thenReturn(_Password);
		when(this.passwordEncoder.matches(_Password_Kaputt, _Password)).thenReturn(false);
		//when
		this.toTest.authenticate(this.authenticationModel);
	}

	private static class TokenMatcher implements ArgumentMatcher<String> {

		@Override
		public boolean matches(String token) {
			return token.length() == 31;
		}
	}
}
