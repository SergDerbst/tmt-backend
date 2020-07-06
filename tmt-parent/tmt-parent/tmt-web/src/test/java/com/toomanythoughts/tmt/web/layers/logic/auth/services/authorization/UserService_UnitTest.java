package com.toomanythoughts.tmt.web.layers.logic.auth.services.authorization;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.toomanythoughts.tmt.web.logic.auth.DayOfBirthService;
import com.toomanythoughts.tmt.web.logic.auth.authentication.model.AuthenticationModel;
import com.toomanythoughts.tmt.web.logic.auth.authorization.model.RoleModel;
import com.toomanythoughts.tmt.web.logic.auth.authorization.model.UserModel;
import com.toomanythoughts.tmt.web.logic.auth.authorization.model.PersonalDataModel.DayOfBirth;
import com.toomanythoughts.tmt.web.logic.auth.authorization.services.RoleService;
import com.toomanythoughts.tmt.web.logic.auth.authorization.services.UserService;
import com.toomanythoughts.tmt.web.persistence.daos.UserDao;
import com.toomanythoughts.tmt.web.persistence.entities.auth.RoleEntity;
import com.toomanythoughts.tmt.web.persistence.entities.auth.UserEntity;
import com.toomanythoughts.tmt.web.persistence.entities.auth.UserSex;
import com.toomanythoughts.tmt.web.persistence.entities.auth.UserTitle;

@RunWith(SpringRunner.class)
public class UserService_UnitTest {

	private static final String _AccessToken = "a wonderful string full of random rubbish";
	private static final String _Email = "dirk.smacker@filthy.com";
	private static final String _FirstName = "Cletus";
	private static final String _LastName = "Damme";
	private static final String _MiddleName = "Van";
	private static final String _Username = "annoying prick";

	@TestConfiguration
	static class UserServiceUnitTestConfiguration {
		@Bean
		public UserService toTest() {
			return new UserService();
		}
	}

	@Autowired
	UserService toTest;
	@MockBean
	UserDao userDao;
	@MockBean
	RoleService roleService;
	@MockBean
	DayOfBirthService dayofBirthService;
	@MockBean
	UserEntity userEntity;
	@MockBean
	DayOfBirth dayOfBirth;
	@MockBean
	AuthenticationModel authenticationModel;

	private final Set<RoleEntity> roleEntities = new HashSet<>();
	private final Set<RoleModel> roleModels = new HashSet<>();

	@Test
	public void entityByUsernameOrEmail_ByEmail() {
		//given
		when(this.authenticationModel.getUsername()).thenReturn(_Email);
		//when
		this.toTest.entityByUsernameOrEmail(this.authenticationModel);
		//then
		verify(this.userDao, times(1)).byEmail(_Email);
		verify(this.userDao, never()).byUsername(_Email);
	}

	@Test
	public void entityByUsernameOrEmail_ByUsername() {
		//given
		when(this.authenticationModel.getUsername()).thenReturn(_Username);
		//when
		this.toTest.entityByUsernameOrEmail(this.authenticationModel);
		//then
		verify(this.userDao, never()).byEmail(_Username);
		verify(this.userDao, times(1)).byUsername(_Username);
	}

	@Test
	public void entityByEmail() {
		//given
		when(this.userDao.byEmail(_Email)).thenReturn(this.userEntity);
		//when
		final UserEntity entity = this.toTest.entityByEmail(_Email);
		//then
		assertThat(entity, equalTo(this.userEntity));
		verify(this.userDao, times(1)).byEmail(_Email);
	}

	@Test
	public void entityByUserName() {
		//given
		when(this.userDao.byUsername(_Username)).thenReturn(this.userEntity);
		//when
		final UserEntity entity = this.toTest.entityByUsername(_Username);
		//then
		assertThat(entity, equalTo(this.userEntity));
		verify(this.userDao, times(1)).byUsername(_Username);
	}

	@Test
	public void save() {
		//given
		this.prepareToModel();
		when(this.userDao.update(this.userEntity)).thenReturn(this.userEntity);
		//when
		final UserModel userModel = this.toTest.save(this.userEntity);
		//then
		verify(this.userDao, times(1)).update(this.userEntity);
		this.assertModel(userModel);
	}

	@Test
	public void toModel() {
		//given
		this.prepareToModel();
		//when
		final UserModel model = this.toTest.toModel(this.userEntity);
		//then
		this.assertModel(model);
	}

	private void assertModel(final UserModel model) {
		assertThat(model.getCredentials().getAccessToken(), equalTo(_AccessToken));
		assertThat(model.getCredentials().getEmail(), equalTo(_Email));
		assertThat(model.isPaymentValidated(), equalTo(false));
		assertThat(model.getPersonalData().getDayOfBirth(), equalTo(this.dayOfBirth));
		assertThat(model.getPersonalData().getFirstName(), equalTo(_FirstName));
		assertThat(model.getPersonalData().getLastName(), equalTo(_LastName));
		assertThat(model.getPersonalData().getMiddleNames(), equalTo(_MiddleName));
		assertThat(model.getPersonalData().getSex(), equalTo(UserSex.Female));
		assertThat(model.getPersonalData().getTitle(), equalTo(UserTitle.FemaleTitle));
		assertThat(model.isPostalValidated(), equalTo(false));
		assertThat(model.getRoles(), equalTo(this.roleModels));
	}

	private void prepareToModel() {
		when(this.userEntity.getAccessToken()).thenReturn(_AccessToken);
		when(this.userEntity.getEmail()).thenReturn(_Email);
		when(this.userEntity.isPaymentValidated()).thenReturn(false);
		when(this.userEntity.isPostalValidated()).thenReturn(false);
		when(this.userEntity.getDayOfBirth()).thenReturn(new Date());
		when(this.userEntity.getFirstName()).thenReturn(_FirstName);
		when(this.userEntity.getLastName()).thenReturn(_LastName);
		when(this.userEntity.getMiddleNames()).thenReturn(_MiddleName);
		when(this.userEntity.getSex()).thenReturn(UserSex.Female);
		when(this.userEntity.getTitle()).thenReturn(UserTitle.FemaleTitle);
		when(this.userEntity.getRoles()).thenReturn(this.roleEntities);
		when(this.roleService.roles(this.roleEntities)).thenReturn(this.roleModels);
		when(this.dayofBirthService.toDayOfBirth(any(Date.class))).thenReturn(this.dayOfBirth);
	}
}
