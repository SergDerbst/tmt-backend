package com.toomanythoughts.tmt.web.layers.logic.auth.services.authentication;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

import com.toomanythoughts.tmt.commons.exceptions.logic.impl.FormDataInvalidRuntimeException;
import com.toomanythoughts.tmt.web.logic.security.authentication.model.RegistrationCredentialsModel;
import com.toomanythoughts.tmt.web.logic.security.authentication.model.RegistrationModel;
import com.toomanythoughts.tmt.web.logic.security.authentication.services.RegistrationDataPreppingService;
import com.toomanythoughts.tmt.web.logic.security.authentication.services.RegistrationService;
import com.toomanythoughts.tmt.web.logic.security.authorization.model.PersonalDataModel;
import com.toomanythoughts.tmt.web.logic.security.authorization.model.RoleModel;
import com.toomanythoughts.tmt.web.logic.security.authorization.model.PersonalDataModel.DayOfBirth;
import com.toomanythoughts.tmt.web.logic.security.authorization.security.RoleService;
import com.toomanythoughts.tmt.web.persistence.entities.security.UserSex;
import com.toomanythoughts.tmt.web.persistence.entities.security.UserTitle;

@RunWith(SpringRunner.class)
public class RegistrationDataPreppingService_UnitTest {

	private static final String _OtherRole = "Troll";
	private static final String _DefaultRole = "Reader";
	private static final String _TotalRubbish = "total rubbish";
	private static final String _PasswordValid = "Preposito_25";
	private static final String _PasswordTooShort = "Prep";
	private static final String _PasswordWithoutNumber = "Preposito_";
	private static final String _PasswordWithoutSpecialCharacter = "Preposito25";
	private static final String _PasswordWithoutLowerCase = "PREPOSITO_25";
	private static final String _PasswordWithoutUpperCase = "preposito_25";
	private static final String _EmailValid = "sausage@cream.info";
	private static final String _UsernameValid = "Wanking Willy";
	private static final String _UsernameTooShort = "Ass";
	private static final String _LastName = "Van Damme";
	private static final String _FirstName = "Cletus";
	private static final int _UserId = 666;

	@TestConfiguration
	static class RegistrationDataPreppingServiceUnitTestConfiguration {
		@Bean
		public RegistrationDataPreppingService toTest() {
			return new RegistrationDataPreppingService();
		}
	}

	@Autowired
	RegistrationDataPreppingService toTest;
	@MockBean
	RegistrationService userService;
	@MockBean
	PasswordEncoder passwordEncoder;
	@MockBean
	RoleService roleService;
	@MockBean
	RegistrationModel registrationModel;
	@MockBean
	PersonalDataModel personalDataModel;
	@MockBean
	RegistrationCredentialsModel registrationCredentialsModel;
	@MockBean
	RoleModel roleModel;

	@Before
	public void setUp() {

	}

	@Test
	public void prep_Success_User_WithPreferredLanguage_Blank() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.validDayOfBirth());
		when(this.personalDataModel.getSex()).thenReturn(UserSex.Female);
		when(this.registrationModel.getCredentials()).thenReturn(this.registrationCredentialsModel);
		when(this.registrationModel.getCredentials().getUsername()).thenReturn(_UsernameValid);
		when(this.registrationModel.getCredentials().getEmail()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getEmailConfirm()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getPassword()).thenReturn(_PasswordValid);
		when(this.registrationModel.getCredentials().getPasswordConfirm()).thenReturn(_PasswordValid);
		when(this.registrationModel.getRoles()).thenReturn(this.additionalRoles());
		when(this.roleModel.getName()).thenReturn(_OtherRole);
		when(this.roleService.findByName(_OtherRole)).thenReturn(this.roleModel);
		when(this.registrationModel.getPreferredLanguage()).thenReturn(" ");
		//when
		this.toTest.prepAndRegister(this.registrationModel);
		//then
		verify(this.registrationModel, times(1)).setPreferredLanguage("en-US");
	}

	@Test
	public void prep_Success_User_WithPreferredLanguage_Empty() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.validDayOfBirth());
		when(this.personalDataModel.getSex()).thenReturn(UserSex.Female);
		when(this.registrationModel.getCredentials()).thenReturn(this.registrationCredentialsModel);
		when(this.registrationModel.getCredentials().getUsername()).thenReturn(_UsernameValid);
		when(this.registrationModel.getCredentials().getEmail()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getEmailConfirm()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getPassword()).thenReturn(_PasswordValid);
		when(this.registrationModel.getCredentials().getPasswordConfirm()).thenReturn(_PasswordValid);
		when(this.registrationModel.getRoles()).thenReturn(this.additionalRoles());
		when(this.roleModel.getName()).thenReturn(_OtherRole);
		when(this.roleService.findByName(_OtherRole)).thenReturn(this.roleModel);
		when(this.registrationModel.getPreferredLanguage()).thenReturn("");
		//when
		this.toTest.prepAndRegister(this.registrationModel);
		//then
		verify(this.registrationModel, times(1)).setPreferredLanguage("en-US");
	}

	@Test
	public void prep_Success_User_WithPreferredLanguage_Null() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.validDayOfBirth());
		when(this.personalDataModel.getSex()).thenReturn(UserSex.Female);
		when(this.registrationModel.getCredentials()).thenReturn(this.registrationCredentialsModel);
		when(this.registrationModel.getCredentials().getUsername()).thenReturn(_UsernameValid);
		when(this.registrationModel.getCredentials().getEmail()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getEmailConfirm()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getPassword()).thenReturn(_PasswordValid);
		when(this.registrationModel.getCredentials().getPasswordConfirm()).thenReturn(_PasswordValid);
		when(this.registrationModel.getRoles()).thenReturn(this.additionalRoles());
		when(this.roleModel.getName()).thenReturn(_OtherRole);
		when(this.roleService.findByName(_OtherRole)).thenReturn(this.roleModel);
		when(this.registrationModel.getPreferredLanguage()).thenReturn(null);
		//when
		this.toTest.prepAndRegister(this.registrationModel);
		//then
		verify(this.registrationModel, times(1)).setPreferredLanguage("en-US");
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithNonExistingRoles() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.validDayOfBirth());
		when(this.personalDataModel.getSex()).thenReturn(UserSex.Female);
		when(this.registrationModel.getCredentials()).thenReturn(this.registrationCredentialsModel);
		when(this.registrationModel.getCredentials().getUsername()).thenReturn(_UsernameValid);
		when(this.registrationModel.getCredentials().getEmail()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getEmailConfirm()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getPassword()).thenReturn(_PasswordValid);
		when(this.registrationModel.getCredentials().getPasswordConfirm()).thenReturn(_PasswordValid);
		when(this.registrationModel.getRoles()).thenReturn(this.additionalRoles());
		when(this.roleModel.getName()).thenReturn(_OtherRole);
		when(this.roleService.findByName(_OtherRole)).thenReturn(null);
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.role.unknown"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.roles"));
			assertThat(e.getContextEntries().get(1).getValue(), equalTo(_OtherRole));
			verify(this.roleService, never()).findDefaultRole();
			verify(this.registrationModel, never()).setRoles(argThat(new OtherRolesMatcher()));
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test
	public void prep_Success_User_WithNonDefaultRoles() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.validDayOfBirth());
		when(this.personalDataModel.getSex()).thenReturn(UserSex.Female);
		when(this.registrationModel.getCredentials()).thenReturn(this.registrationCredentialsModel);
		when(this.registrationModel.getCredentials().getUsername()).thenReturn(_UsernameValid);
		when(this.registrationModel.getCredentials().getEmail()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getEmailConfirm()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getPassword()).thenReturn(_PasswordValid);
		when(this.registrationModel.getCredentials().getPasswordConfirm()).thenReturn(_PasswordValid);
		when(this.registrationModel.getRoles()).thenReturn(this.additionalRoles());
		when(this.roleModel.getName()).thenReturn(_OtherRole);
		when(this.roleService.findByName(_OtherRole)).thenReturn(this.roleModel);
		//when
		this.toTest.prepAndRegister(this.registrationModel);
		//then
		verify(this.roleService, never()).findDefaultRole();
		verify(this.registrationModel, times(1)).setRoles(argThat(new OtherRolesMatcher()));
	}

	@Test
	public void prep_Success_User_WithRoles_Empty() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.validDayOfBirth());
		when(this.personalDataModel.getSex()).thenReturn(UserSex.Female);
		when(this.registrationModel.getCredentials()).thenReturn(this.registrationCredentialsModel);
		when(this.registrationModel.getCredentials().getUsername()).thenReturn(_UsernameValid);
		when(this.registrationModel.getCredentials().getEmail()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getEmailConfirm()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getPassword()).thenReturn(_PasswordValid);
		when(this.registrationModel.getCredentials().getPasswordConfirm()).thenReturn(_PasswordValid);
		when(this.registrationModel.getRoles()).thenReturn(new HashSet<RoleModel>());
		when(this.roleService.findDefaultRole()).thenReturn(this.roleModel);
		when(this.roleModel.getName()).thenReturn(_DefaultRole);
		//when
		this.toTest.prepAndRegister(this.registrationModel);
		//then
		verify(this.roleService, times(1)).findDefaultRole();
		verify(this.registrationModel, times(1)).setRoles(argThat(new DefaultRolesMatcher()));
	}

	@Test
	public void prep_Success_User_WithRoles_Null() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.validDayOfBirth());
		when(this.personalDataModel.getSex()).thenReturn(UserSex.Female);
		when(this.registrationModel.getCredentials()).thenReturn(this.registrationCredentialsModel);
		when(this.registrationModel.getCredentials().getUsername()).thenReturn(_UsernameValid);
		when(this.registrationModel.getCredentials().getEmail()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getEmailConfirm()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getPassword()).thenReturn(_PasswordValid);
		when(this.registrationModel.getCredentials().getPasswordConfirm()).thenReturn(_PasswordValid);
		when(this.registrationModel.getRoles()).thenReturn(null);
		when(this.roleService.findDefaultRole()).thenReturn(this.roleModel);
		when(this.roleModel.getName()).thenReturn(_DefaultRole);
		//when
		this.toTest.prepAndRegister(this.registrationModel);
		//then
		verify(this.roleService, times(1)).findDefaultRole();
		verify(this.registrationModel, times(1)).setRoles(argThat(new DefaultRolesMatcher()));
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithCredentials_Password_TooShort() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.validDayOfBirth());
		when(this.personalDataModel.getSex()).thenReturn(UserSex.Female);
		when(this.registrationModel.getCredentials()).thenReturn(this.registrationCredentialsModel);
		when(this.registrationModel.getCredentials().getUsername()).thenReturn(_UsernameValid);
		when(this.registrationModel.getCredentials().getEmail()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getEmailConfirm()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getPassword()).thenReturn(_PasswordTooShort);
		when(this.registrationModel.getCredentials().getPasswordConfirm()).thenReturn(_PasswordTooShort);
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.credentials.with.password.invalid"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.credentials.password"));
			assertThat(e.getContextEntries().get(1).getValue(), equalTo(_PasswordTooShort));
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithCredentials_Password_WithoutNumber() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.validDayOfBirth());
		when(this.personalDataModel.getSex()).thenReturn(UserSex.Female);
		when(this.registrationModel.getCredentials()).thenReturn(this.registrationCredentialsModel);
		when(this.registrationModel.getCredentials().getUsername()).thenReturn(_UsernameValid);
		when(this.registrationModel.getCredentials().getEmail()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getEmailConfirm()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getPassword()).thenReturn(_PasswordWithoutNumber);
		when(this.registrationModel.getCredentials().getPasswordConfirm()).thenReturn(_PasswordWithoutNumber);
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.credentials.with.password.invalid"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.credentials.password"));
			assertThat(e.getContextEntries().get(1).getValue(), equalTo(_PasswordWithoutNumber));
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithCredentials_Password_WithoutSpecialCharacter() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.validDayOfBirth());
		when(this.personalDataModel.getSex()).thenReturn(UserSex.Female);
		when(this.registrationModel.getCredentials()).thenReturn(this.registrationCredentialsModel);
		when(this.registrationModel.getCredentials().getUsername()).thenReturn(_UsernameValid);
		when(this.registrationModel.getCredentials().getEmail()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getEmailConfirm()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getPassword()).thenReturn(_PasswordWithoutSpecialCharacter);
		when(this.registrationModel.getCredentials().getPasswordConfirm()).thenReturn(_PasswordWithoutSpecialCharacter);
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.credentials.with.password.invalid"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.credentials.password"));
			assertThat(e.getContextEntries().get(1).getValue(), equalTo(_PasswordWithoutSpecialCharacter));
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithCredentials_Password_WithoutLowerCase() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.validDayOfBirth());
		when(this.personalDataModel.getSex()).thenReturn(UserSex.Female);
		when(this.registrationModel.getCredentials()).thenReturn(this.registrationCredentialsModel);
		when(this.registrationModel.getCredentials().getUsername()).thenReturn(_UsernameValid);
		when(this.registrationModel.getCredentials().getEmail()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getEmailConfirm()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getPassword()).thenReturn(_PasswordWithoutLowerCase);
		when(this.registrationModel.getCredentials().getPasswordConfirm()).thenReturn(_PasswordWithoutLowerCase);
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.credentials.with.password.invalid"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.credentials.password"));
			assertThat(e.getContextEntries().get(1).getValue(), equalTo(_PasswordWithoutLowerCase));
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithCredentials_Password_WithoutUpperCase() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.validDayOfBirth());
		when(this.personalDataModel.getSex()).thenReturn(UserSex.Female);
		when(this.registrationModel.getCredentials()).thenReturn(this.registrationCredentialsModel);
		when(this.registrationModel.getCredentials().getUsername()).thenReturn(_UsernameValid);
		when(this.registrationModel.getCredentials().getEmail()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getEmailConfirm()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getPassword()).thenReturn(_PasswordWithoutUpperCase);
		when(this.registrationModel.getCredentials().getPasswordConfirm()).thenReturn(_PasswordWithoutUpperCase);
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.credentials.with.password.invalid"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.credentials.password"));
			assertThat(e.getContextEntries().get(1).getValue(), equalTo(_PasswordWithoutUpperCase));
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithCredentials_PasswordConfirmation_NotMatching() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.validDayOfBirth());
		when(this.personalDataModel.getSex()).thenReturn(UserSex.Female);
		when(this.registrationModel.getCredentials()).thenReturn(this.registrationCredentialsModel);
		when(this.registrationModel.getCredentials().getUsername()).thenReturn(_UsernameValid);
		when(this.registrationModel.getCredentials().getEmail()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getEmailConfirm()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getPassword()).thenReturn(_PasswordValid);
		when(this.registrationModel.getCredentials().getPasswordConfirm()).thenReturn(_TotalRubbish);
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.credentials.with.password.confirmation.not.matching"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.credentials.password && user.credentials.passwordConfirm"));
			assertThat(e.getContextEntries().get(1).getValue(), equalTo(_PasswordValid + " && " + _TotalRubbish));
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithCredentials_Password_Blank() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.validDayOfBirth());
		when(this.personalDataModel.getSex()).thenReturn(UserSex.Female);
		when(this.registrationModel.getCredentials()).thenReturn(this.registrationCredentialsModel);
		when(this.registrationModel.getCredentials().getUsername()).thenReturn(_UsernameValid);
		when(this.registrationModel.getCredentials().getEmail()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getEmailConfirm()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getPassword()).thenReturn(" ");
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.credentials.without.password"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.credentials.password"));
			assertThat(e.getContextEntries().get(1).getValue(), nullValue());
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithCredentials_Password_Null() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.validDayOfBirth());
		when(this.personalDataModel.getSex()).thenReturn(UserSex.Female);
		when(this.registrationModel.getCredentials()).thenReturn(this.registrationCredentialsModel);
		when(this.registrationModel.getCredentials().getUsername()).thenReturn(_UsernameValid);
		when(this.registrationModel.getCredentials().getEmail()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getEmailConfirm()).thenReturn(_EmailValid);
		when(this.registrationModel.getCredentials().getPassword()).thenReturn(null);
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.credentials.without.password"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.credentials.password"));
			assertThat(e.getContextEntries().get(1).getValue(), nullValue());
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithCredentials_Email_Invalid() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.validDayOfBirth());
		when(this.personalDataModel.getSex()).thenReturn(UserSex.Female);
		when(this.registrationModel.getCredentials()).thenReturn(this.registrationCredentialsModel);
		when(this.registrationModel.getCredentials().getUsername()).thenReturn(_UsernameValid);
		when(this.registrationModel.getCredentials().getEmail()).thenReturn("sausage");
		when(this.registrationModel.getCredentials().getEmailConfirm()).thenReturn("sausage");
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.credentials.with.email.address.invalid"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.credentials.email"));
			assertThat(e.getContextEntries().get(1).getValue(), equalTo("sausage"));
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithCredentials_EmailConfirmation_NotMatching() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.validDayOfBirth());
		when(this.personalDataModel.getSex()).thenReturn(UserSex.Female);
		when(this.registrationModel.getCredentials()).thenReturn(this.registrationCredentialsModel);
		when(this.registrationModel.getCredentials().getUsername()).thenReturn(_UsernameValid);
		when(this.registrationModel.getCredentials().getEmail()).thenReturn("sausage");
		when(this.registrationModel.getCredentials().getEmailConfirm()).thenReturn("cream");
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.credentials.with.email.address.confirmation.not.matching"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.credentials.email && user.credentials.emailConfirm"));
			assertThat(e.getContextEntries().get(1).getValue(), equalTo("sausage && cream"));
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithCredentials_EmailConfirmation_Null() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.validDayOfBirth());
		when(this.personalDataModel.getSex()).thenReturn(UserSex.Female);
		when(this.registrationModel.getCredentials()).thenReturn(this.registrationCredentialsModel);
		when(this.registrationModel.getCredentials().getUsername()).thenReturn(_UsernameValid);
		when(this.registrationModel.getCredentials().getEmail()).thenReturn("");
		when(this.registrationModel.getCredentials().getEmailConfirm()).thenReturn(null);
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.credentials.without.email.address.confirmation"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.credentials.emailConfirm"));
			assertThat(e.getContextEntries().get(1).getValue(), nullValue());
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithCredentials_Email_Null() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.validDayOfBirth());
		when(this.personalDataModel.getSex()).thenReturn(UserSex.Female);
		when(this.registrationModel.getCredentials()).thenReturn(this.registrationCredentialsModel);
		when(this.registrationModel.getCredentials().getUsername()).thenReturn(_UsernameValid);
		when(this.registrationModel.getCredentials().getEmail()).thenReturn(null);
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.credentials.without.email.address"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.credentials.email"));
			assertThat(e.getContextEntries().get(1).getValue(), nullValue());
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithCredentials_Username_TooShort() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.validDayOfBirth());
		when(this.personalDataModel.getSex()).thenReturn(UserSex.Female);
		when(this.registrationModel.getCredentials()).thenReturn(this.registrationCredentialsModel);
		when(this.registrationModel.getCredentials().getUsername()).thenReturn(_UsernameTooShort);
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.credentials.username.minLength"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.credentials.username"));
			assertThat(e.getContextEntries().get(1).getValue(), equalTo(_UsernameTooShort));
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithCredentials_Username_Null() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.validDayOfBirth());
		when(this.personalDataModel.getSex()).thenReturn(UserSex.Female);
		when(this.registrationModel.getCredentials()).thenReturn(this.registrationCredentialsModel);
		when(this.registrationModel.getCredentials().getUsername()).thenReturn(null);
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.credentials.without.username"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.credentials.username"));
			assertThat(e.getContextEntries().get(1).getValue(), nullValue());
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithoutCredentials() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.validDayOfBirth());
		when(this.personalDataModel.getSex()).thenReturn(UserSex.Female);
		when(this.registrationModel.getCredentials()).thenReturn(null);
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.without.credentials"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.credentials"));
			assertThat(e.getContextEntries().get(1).getValue(), nullValue());
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithPersonalData_Sex_Null() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.validDayOfBirth());
		when(this.personalDataModel.getSex()).thenReturn(null);
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.personalData.without.sex"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.personalData.sex"));
			assertThat(e.getContextEntries().get(1).getValue(), nullValue());
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithPersonalData_DayOfBirth_TooOld() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.tooOldDayOfBirth());
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.personalData.with.dayOfBirth.tooOld"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.personalData.dayOfBirth"));
			assertThat(e.getContextEntries().get(1).getValue(), equalTo(this.tooOldDayOfBirth()));
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithPersonalData_DayOfBirth_TooYoung() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.tooYoungDayOfBirth());
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.personalData.with.dayOfBirth.tooYoung"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.personalData.dayOfBirth"));
			assertThat(e.getContextEntries().get(1).getValue(), equalTo(this.tooYoungDayOfBirth()));
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithPersonalData_DayOfBirth_Future() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.futureDayOfBirth());
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.personalData.with.dayOfBirth.future"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.personalData.dayOfBirth"));
			assertThat(e.getContextEntries().get(1).getValue(), equalTo(this.futureDayOfBirth()));
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithPersonalData_DayOfBirth_Null() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(null);
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.personalData.without.dayOfBirth"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.personalData.dayOfBirth"));
			assertThat(e.getContextEntries().get(1).getValue(), equalTo(null));
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithPersonalData_LastName_Empty() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn("");
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.personalData.without.lastName"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.personalData.lastName"));
			assertThat(e.getContextEntries().get(1).getValue(), equalTo(""));
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithPersonalData_LastName_Null() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(null);
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.personalData.without.lastName"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.personalData.lastName"));
			assertThat(e.getContextEntries().get(1).getValue(), nullValue());
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithPersonalData_FirstName_Empty() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn("");
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.personalData.without.firstName"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.personalData.firstName"));
			assertThat(e.getContextEntries().get(1).getValue(), equalTo(""));
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithPersonalData_FirstName_Null() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.personalDataModel.getFirstName()).thenReturn(null);
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.personalData.without.firstName"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.personalData.firstName"));
			assertThat(e.getContextEntries().get(1).getValue(), nullValue());
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithPersonalData_Title_Null() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.personalDataModel.getTitle()).thenReturn(null);
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.personalData.without.title"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.personalData.title"));
			assertThat(e.getContextEntries().get(1).getValue(), nullValue());
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithoutPersonalData() {
		//given
		when(this.registrationModel.getId()).thenReturn(null);
		when(this.registrationModel.getPersonalData()).thenReturn(null);
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.without.personalData"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.personalData"));
			assertThat(e.getContextEntries().get(1).getValue(), nullValue());
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	@Test(expected = FormDataInvalidRuntimeException.class)
	public void prep_Failure_User_WithId() {
		//given
		when(this.registrationModel.getId()).thenReturn(_UserId);
		//when
		try {
			this.toTest.prepAndRegister(this.registrationModel);
		} catch (final FormDataInvalidRuntimeException e) {
			//then
			assertThat(e.getMessage(), startsWith("error.message.register.user.with.id"));
			assertThat(e.getContextEntries().size(), equalTo(2));
			assertThat(e.getContextEntries().get(0).getValue(), equalTo("user.id"));
			assertThat(e.getContextEntries().get(1).getValue(), equalTo(_UserId));
			verify(this.userService, never()).create(this.registrationModel);
			throw e;
		}
	}

	private DayOfBirth validDayOfBirth() {
		final DayOfBirth dayOfBirth = new DayOfBirth();
		final LocalDate today = LocalDate.now();
		dayOfBirth.setDay(today.getDayOfMonth());
		dayOfBirth.setMonth(today.getMonthValue());
		dayOfBirth.setYear(today.getYear() - 15);
		return dayOfBirth	;
	}

	private DayOfBirth tooYoungDayOfBirth() {
		final DayOfBirth dayOfBirth = new DayOfBirth();
		final LocalDate today = LocalDate.now();
		dayOfBirth.setDay(today.getDayOfMonth());
		dayOfBirth.setMonth(today.getMonthValue());
		dayOfBirth.setYear(today.getYear() - 13);
		return dayOfBirth	;
	}

	private DayOfBirth tooOldDayOfBirth() {
		final DayOfBirth dayOfBirth = new DayOfBirth();
		final LocalDate today = LocalDate.now();
		dayOfBirth.setDay(today.getDayOfMonth());
		dayOfBirth.setMonth(today.getMonthValue());
		dayOfBirth.setYear(today.getYear() - 121);
		return dayOfBirth	;
	}

	private DayOfBirth futureDayOfBirth() {
		final DayOfBirth dayOfBirth = new DayOfBirth();
		final LocalDate today = LocalDate.now();
		dayOfBirth.setDay(today.getDayOfMonth());
		dayOfBirth.setMonth(today.getMonthValue());
		dayOfBirth.setYear(today.getYear() + 1);
		return dayOfBirth	;
	}

	private Set<RoleModel> additionalRoles() {
		final HashSet<RoleModel> roleModels = new HashSet<>();
		roleModels.add(this.roleModel);
		return roleModels;
	}

	private static class OtherRolesMatcher implements ArgumentMatcher<Set<RoleModel>> {

		@Override
		public boolean matches(Set<RoleModel> roleModels) {
			boolean matches = false;
			if (roleModels == null || roleModels.isEmpty()) {
				matches = false;
			}
			if (roleModels.size() > 1) {
				matches =  false;
			}
			for (final RoleModel role : roleModels) {
				if (role.getName().equals(_OtherRole)) {
					matches =  true;
				}
			}
			return matches;
		}
	}

	private static class DefaultRolesMatcher implements ArgumentMatcher<Set<RoleModel>> {

		@Override
		public boolean matches(Set<RoleModel> roleModels) {
			boolean matches = false;
			if (roleModels == null || roleModels.isEmpty()) {
				matches = false;
			}
			if (roleModels.size() > 1) {
				matches =  false;
			}
			for (final RoleModel role : roleModels) {
				if (role.getName().equals(_DefaultRole)) {
					matches =  true;
				}
			}
			return matches;
		}
	}
}
