package com.toomanythoughts.tmt.web.layers.logic.auth.services.authentication;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasLength;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.toomanythoughts.tmt.web.layers.logic.auth.model.authentication.EmailVerificationModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.authentication.RegistrationCredentialsModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.authentication.RegistrationModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.authorization.PersonalDataModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.authorization.PersonalDataModel.DayOfBirth;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.authorization.RoleModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.services.DayOfBirthService;
import com.toomanythoughts.tmt.web.layers.logic.auth.services.authorization.RoleService;
import com.toomanythoughts.tmt.web.layers.persistence.daos.UserDao;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.RoleEntity;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.UserEntity;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.UserSex;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.UserTitle;

@RunWith(SpringRunner.class)
public class RegistrationServiceUnitTest {

	private static final int _UserId = 666;
	private static final String _Email = "sausage@cream.info";
	private static final String _EmailValidationKey = "20010911";
	private static final String _FirstName = "Cletus";
	private static final String _LastName = "Damme";
	private static final String _MiddleName = "Van";
	private static final String _Password = "LSD_25";
	private static final String _PreferredLanguage = "mumbo jumbo";
	private static final String _Username = "Annoying Prick";

	@TestConfiguration
	static class RegistrationServiceUnitTestConfiguration {
		@Bean
		public RegistrationService toTest() {
			return new RegistrationService();
		}
	}

	@Autowired
	RegistrationService toTest;
	@MockBean
	UserDao userDao;
	@MockBean
	RoleService roleService;
	@MockBean
	EmailVerificationService emailService;
	@MockBean
	DayOfBirthService dayOfBirthService;
	@MockBean
	RegistrationModel registrationModel;
	@MockBean
	PersonalDataModel personalDataModel;
	@MockBean
	RegistrationCredentialsModel registrationCredentialsModel;
	@MockBean
	DayOfBirth dayOfBirth;
	@MockBean
	RoleModel roleModel;
	@MockBean
	RoleEntity roleEntity;
	@MockBean
	UserEntity userEntity;
	@MockBean
	EmailVerificationModel emailVerificationModel;

	private final Set<RoleModel> roleModels = new HashSet<>();
	private final Set<RoleEntity> roleEntities = new HashSet<>();

	@Test
	public void create() {
		//given
		this.prepareToEntity(true);
		this.prepareToModel(true);
		when(this.userDao.create(any(UserEntity.class))).thenReturn(this.userEntity);
		when(this.emailService.sendHtmlMail(any(EmailVerificationModel.class))).thenReturn(this.emailVerificationModel);
			//when
		final EmailVerificationModel emailVerificationModel = this.toTest.create(this.registrationModel);
		//then
		assertEquals(this.emailVerificationModel, emailVerificationModel);
		verify(this.userDao, times(1)).create(any(UserEntity.class));
		verify(this.emailService, times(1)).sendHtmlMail(any(EmailVerificationModel.class));
	}

	@Test
	public void toModel_WithoutRoles() {
		//given
		this.prepareToModel(false);
		//when
		final EmailVerificationModel emailVerificationModel = this.toTest.toModel(this.userEntity);
		//then
		assertThat(emailVerificationModel.getPersonalData().getDayOfBirth(), equalTo(this.dayOfBirth));
		assertThat(emailVerificationModel.getPersonalData().getFirstName(), equalTo(_FirstName));
		assertThat(emailVerificationModel.getPersonalData().getLastName(), equalTo(_LastName));
		assertThat(emailVerificationModel.getPersonalData().getMiddleNames(), equalTo(_MiddleName));
		assertThat(emailVerificationModel.getPersonalData().getSex(), equalTo(UserSex.Female));
		assertThat(emailVerificationModel.getPersonalData().getTitle(), equalTo(UserTitle.FemaleTitle));
		assertThat(emailVerificationModel.getCredentials().getEmail(), equalTo(_Email));
		assertThat(emailVerificationModel.getCredentials().getUsername(), equalTo(_Username));
		assertThat(emailVerificationModel.getId(), equalTo(_UserId));
		assertThat(emailVerificationModel.isEmailValidated(), equalTo(false));
		assertThat(emailVerificationModel.getEmailValidationKey(), equalTo(_EmailValidationKey));
		assertThat(emailVerificationModel.getPreferredLanguage(), equalTo(_PreferredLanguage));
		assertThat(emailVerificationModel.getRoles(), notNullValue());
		assertThat(emailVerificationModel.getRoles().size(), equalTo(0));
		verify(this.dayOfBirthService, times(1)).toDayOfBirth(any(Date.class));
	}

	@Test
	public void toModel_WithRoles() {
		//given
		this.prepareToModel(true);
		//when
		final EmailVerificationModel emailVerificationModel = this.toTest.toModel(this.userEntity);
		//then
		assertThat(emailVerificationModel.getPersonalData().getDayOfBirth(), equalTo(this.dayOfBirth));
		assertThat(emailVerificationModel.getPersonalData().getFirstName(), equalTo(_FirstName));
		assertThat(emailVerificationModel.getPersonalData().getLastName(), equalTo(_LastName));
		assertThat(emailVerificationModel.getPersonalData().getMiddleNames(), equalTo(_MiddleName));
		assertThat(emailVerificationModel.getPersonalData().getSex(), equalTo(UserSex.Female));
		assertThat(emailVerificationModel.getPersonalData().getTitle(), equalTo(UserTitle.FemaleTitle));
		assertThat(emailVerificationModel.getCredentials().getEmail(), equalTo(_Email));
		assertThat(emailVerificationModel.getCredentials().getUsername(), equalTo(_Username));
		assertThat(emailVerificationModel.getId(), equalTo(_UserId));
		assertThat(emailVerificationModel.isEmailValidated(), equalTo(false));
		assertThat(emailVerificationModel.getEmailValidationKey(), equalTo(_EmailValidationKey));
		assertThat(emailVerificationModel.getPreferredLanguage(), equalTo(_PreferredLanguage));
		assertThat(emailVerificationModel.getRoles(), notNullValue());
		assertThat(emailVerificationModel.getRoles().size(), equalTo(1));
		assertThat(emailVerificationModel.getRoles().toArray()[0], equalTo(this.roleModel));
		verify(this.dayOfBirthService, times(1)).toDayOfBirth(any(Date.class));
	}

	@Test
	public void toEntity_WithoutRoles() {
		//given
		this.prepareToEntity(false);
		//when
		final UserEntity userEntity = this.toTest.toEntity(this.registrationModel);
		//then
		assertThat(userEntity.getDayOfBirth(), today());
		assertThat(userEntity.getEmail(), equalTo(_Email));
		assertThat(userEntity.getEmailValidationKey(), hasLength(9));
		assertThat(userEntity.getFirstName(), equalTo(_FirstName));
		assertThat(userEntity.getId(), equalTo(_UserId));
		assertThat(userEntity.getLastName(), equalTo(_LastName));
		assertThat(userEntity.getPassword(), equalTo(_Password));
		assertThat(userEntity.getPreferredLanguage(), equalTo(_PreferredLanguage));
		assertThat(userEntity.getRoles(), notNullValue());
		assertThat(userEntity.getRoles().size(), equalTo(0));
		assertThat(userEntity.getSex(), equalTo(UserSex.Female));
		assertThat(userEntity.getTitle(), equalTo(UserTitle.FemaleTitle));
		assertThat(userEntity.getUsername(), equalTo(_Username));
		verify(this.dayOfBirthService, times(1)).toDate(this.dayOfBirth);
	}

	@Test
	public void toEntity_WithRoles() {
		this.prepareToEntity(true);
		//when
		final UserEntity userEntity = this.toTest.toEntity(this.registrationModel);
		//then
		assertThat(userEntity.getDayOfBirth(), today());
		assertThat(userEntity.getEmail(), equalTo(_Email));
		assertThat(userEntity.getEmailValidationKey(), hasLength(9));
		assertThat(userEntity.getFirstName(), equalTo(_FirstName));
		assertThat(userEntity.getId(), equalTo(_UserId));
		assertThat(userEntity.getLastName(), equalTo(_LastName));
		assertThat(userEntity.getPassword(), equalTo(_Password));
		assertThat(userEntity.getPreferredLanguage(), equalTo(_PreferredLanguage));
		assertThat(userEntity.getRoles(), notNullValue());
		assertThat(userEntity.getRoles().size(), equalTo(1));
		assertThat(userEntity.getRoles().toArray()[0], equalTo(this.roleEntity));
		assertThat(userEntity.getSex(), equalTo(UserSex.Female));
		assertThat(userEntity.getTitle(), equalTo(UserTitle.FemaleTitle));
		assertThat(userEntity.getUsername(), equalTo(_Username));
		verify(this.dayOfBirthService, times(1)).toDate(this.dayOfBirth);
	}

	private void prepareToModel(final boolean withRoles) {
		if (withRoles) {
			this.roleEntities.add(this.roleEntity);
			this.roleModels.add(this.roleModel);
		}
		when(this.dayOfBirthService.toDayOfBirth(any(Date.class))).thenReturn(this.dayOfBirth);
		when(this.roleService.toModel(this.roleEntity)).thenReturn(this.roleModel);
		when(this.userEntity.getDayOfBirth()).thenReturn(new Date());
		when(this.userEntity.getEmail()).thenReturn(_Email);
		when(this.userEntity.getEmailValidationKey()).thenReturn(_EmailValidationKey);
		when(this.userEntity.getFirstName()).thenReturn(_FirstName);
		when(this.userEntity.getId()).thenReturn(_UserId);
		when(this.userEntity.getLastName()).thenReturn(_LastName);
		when(this.userEntity.getMiddleNames()).thenReturn(_MiddleName);
		when(this.userEntity.getSex()).thenReturn(UserSex.Female);
		when(this.userEntity.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.userEntity.getUsername()).thenReturn(_Username);
		when(this.userEntity.getPreferredLanguage()).thenReturn(_PreferredLanguage);
		when(this.userEntity.getRoles()).thenReturn(this.roleEntities);
		when(this.userEntity.isEmailValidated()).thenReturn(false);
	}

	private void prepareToEntity(boolean withRoles) {
		if (withRoles) {
			this.roleModels.add(this.roleModel);
			this.roleEntities.add(this.roleEntity);
		}
		when(this.registrationModel.getPersonalData()).thenReturn(this.personalDataModel);
		when(this.registrationModel.getCredentials()).thenReturn(this.registrationCredentialsModel);
		when(this.registrationModel.getId()).thenReturn(_UserId);
		when(this.registrationModel.getPreferredLanguage()).thenReturn(_PreferredLanguage);
		when(this.registrationModel.getRoles()).thenReturn(this.roleModels);
		when(this.personalDataModel.getDayOfBirth()).thenReturn(this.dayOfBirth);
		when(this.personalDataModel.getFirstName()).thenReturn(_FirstName);
		when(this.personalDataModel.getLastName()).thenReturn(_LastName);
		when(this.personalDataModel.getMiddleNames()).thenReturn(_MiddleName);
		when(this.personalDataModel.getSex()).thenReturn(UserSex.Female);
		when(this.personalDataModel.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.dayOfBirthService.toDate(this.dayOfBirth)).thenReturn(new Date());
		when(this.registrationCredentialsModel.getEmail()).thenReturn(_Email);
		when(this.registrationCredentialsModel.getPassword()).thenReturn(_Password);
		when(this.registrationCredentialsModel.getUsername()).thenReturn(_Username);
		when(this.roleService.toEntity(this.roleModel)).thenReturn(this.roleEntity);
	}

	private static TodayMatcher today() {
		return new TodayMatcher();
	}

	private static class TodayMatcher extends TypeSafeMatcher<Date> {

		@Override
		public void describeTo(Description description) {
			description.appendText("Date value represents today.");
		}

		@Override
		protected boolean matchesSafely(Date date) {
			final LocalDate now = LocalDate.now();
			final LocalDate value = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
			return now.getYear() == value.getYear() &&
						 now.getMonthValue() == value.getMonthValue() &&
						 now.getDayOfMonth() == value.getDayOfMonth();
		}
	}
}
