package com.toomanythoughts.tmt.web.layers.logic.auth.services.authentication;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.toomanythoughts.tmt.web.exceptions.auth.EmailAlreadyExistsException;
import com.toomanythoughts.tmt.web.exceptions.auth.UsernameAlreadyExistsException;
import com.toomanythoughts.tmt.web.logic.auth.authentication.services.RegistrationDataValidationService;
import com.toomanythoughts.tmt.web.logic.auth.authorization.services.UserService;
import com.toomanythoughts.tmt.web.persistence.entities.auth.UserEntity;

@RunWith(SpringRunner.class)
public class RegistrationDataValidationService_UnitTest {

	private static final String _Username = "Vollspacken";
	private static final String _Email = "sausage@cream.info";

	@TestConfiguration
	static class RegistrationDataValidationServiceUnitTestConfiguration {
		@Bean
		public RegistrationDataValidationService toTest() {
			return new RegistrationDataValidationService();
		}
	}

	@Autowired
	RegistrationDataValidationService toTest;
	@MockBean
	UserService userService;
	@MockBean
	UserEntity userEntity;

	@Test(expected = UsernameAlreadyExistsException.class)
	public void validateUsername_AlreadyExists() throws UsernameAlreadyExistsException {
		//given
		when(this.userService.entityByUsername(_Username)).thenReturn(this.userEntity);
		//when
		this.toTest.validateUsername(_Username);
	}

	@Test(expected = EmailAlreadyExistsException.class)
	public void validateEmail_AlreadyExists() throws EmailAlreadyExistsException {
		//given
		when(this.userService.entityByEmail(_Email)).thenReturn(this.userEntity);
		//when
		this.toTest.validateEmail(_Email);
	}
}
