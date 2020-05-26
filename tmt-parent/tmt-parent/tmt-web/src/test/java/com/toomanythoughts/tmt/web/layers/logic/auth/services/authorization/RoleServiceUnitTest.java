package com.toomanythoughts.tmt.web.layers.logic.auth.services.authorization;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.toomanythoughts.tmt.web.layers.logic.auth.model.authorization.PermissionModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.authorization.RoleModel;
import com.toomanythoughts.tmt.web.layers.persistence.daos.RoleDao;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.PermissionEntity;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.RoleEntity;

@RunWith(SpringRunner.class)
public class RoleServiceUnitTest {

	private static final int _RoleId = 666;
	private static final String _RoleName = "doofus";
	private static final String _DefaultRoleName = "Reader";
	private static final long _RoleCount = 23;

	@TestConfiguration
	static class RoleServiceUnitTestConfiguration {
		@Bean
		public RoleService toTest() {
			return new RoleService();
		}
	}

	@Autowired
	RoleService toTest;
	@MockBean
	RoleDao roleDao;
	@MockBean
	PermissionService permissionService;
	@MockBean
	RoleEntity roleEntity;
	@MockBean
	RoleModel roleModel;
	@MockBean
	PermissionEntity permissionEntity;
	@MockBean
	PermissionModel permissionModel;

	private final Set<PermissionEntity> permissionEntities = new HashSet<>();
	private final Set<PermissionModel> permissionModels = new HashSet<>();
	private final Set<RoleEntity> roleEntities = new HashSet<>();

	@Test
	public void roles_WithRoles() {
		//given
		this.roleEntities.add(this.roleEntity);
		this.permissionEntities.add(this.permissionEntity);
		when(this.roleEntity.getId()).thenReturn(_RoleId);
		when(this.roleEntity.getName()).thenReturn(_RoleName);
		when(this.roleEntity.getPermissions()).thenReturn(this.permissionEntities);
		when(this.permissionService.toModel(this.permissionEntity)).thenReturn(this.permissionModel);
		//when
		final Set<RoleModel> roles = this.toTest.roles(this.roleEntities);
		//then
		assertThat(roles, notNullValue());
		assertThat(roles.size(), equalTo(1));
		final RoleModel model = (RoleModel) roles.toArray()[0];
		assertThat(model.getId(), equalTo(_RoleId));
		assertThat(model.getName(), equalTo(_RoleName));
		assertThat(model.getPermissions(), notNullValue());
		assertThat(model.getPermissions().size(), equalTo(1));
		assertThat(model.getPermissions().toArray()[0], equalTo(this.permissionModel));
	}

	@Test
	public void roles_Empty() {
		//when
		final Set<RoleModel> roles = this.toTest.roles(this.roleEntities);
		//then
		assertThat(roles, notNullValue());
		assertThat(roles.size(), equalTo(0));
	}

	@Test
	public void findDefaultRole() {
		//given
		when(this.roleDao.getByName(_DefaultRoleName)).thenReturn(this.roleEntity);
		this.permissionEntities.add(this.permissionEntity);
		when(this.roleEntity.getId()).thenReturn(_RoleId);
		when(this.roleEntity.getName()).thenReturn(_DefaultRoleName);
		when(this.roleEntity.getPermissions()).thenReturn(this.permissionEntities);
		when(this.permissionService.toModel(this.permissionEntity)).thenReturn(this.permissionModel);
		//when
		final RoleModel model = this.toTest.findDefaultRole();
		//then
		assertThat(model.getId(), equalTo(_RoleId));
		assertThat(model.getName(), equalTo(_DefaultRoleName));
		assertThat(model.getPermissions(), notNullValue());
		assertThat(model.getPermissions().size(), equalTo(1));
		assertThat(model.getPermissions().toArray()[0], equalTo(this.permissionModel));
		verify(this.roleDao, times(1)).getByName(_DefaultRoleName);
	}

	@Test
	public void findByName_NotFound() {
		//given
		when(this.roleDao.getByName(_RoleName)).thenReturn(null);
		//when
		final RoleModel model = this.toTest.findByName(_RoleName);
		//then
		assertThat(model, nullValue());
	}

	@Test
	public void findByName_Found() {
		//given
		when(this.roleDao.getByName(_RoleName)).thenReturn(this.roleEntity);
		this.permissionEntities.add(this.permissionEntity);
		when(this.roleEntity.getId()).thenReturn(_RoleId);
		when(this.roleEntity.getName()).thenReturn(_RoleName);
		when(this.roleEntity.getPermissions()).thenReturn(this.permissionEntities);
		when(this.permissionService.toModel(this.permissionEntity)).thenReturn(this.permissionModel);
		//when
		final RoleModel model = this.toTest.findByName(_RoleName);
		//then
		assertThat(model.getId(), equalTo(_RoleId));
		assertThat(model.getName(), equalTo(_RoleName));
		assertThat(model.getPermissions(), notNullValue());
		assertThat(model.getPermissions().size(), equalTo(1));
		assertThat(model.getPermissions().toArray()[0], equalTo(this.permissionModel));
	}

	@Test
	public void ensureRoleExists_WithRole() {
		//given
		when(this.roleDao.getByName(_RoleName)).thenReturn(this.roleEntity);
		//when
		this.toTest.ensureRoleExists(_RoleName);
		//then
		verify(this.roleDao, never()).create(argThat(byName(_RoleName)));
	}

	@Test
	public void ensureRoleExists_WithoutRole() {
		//given
		when(this.roleDao.getByName(_RoleName)).thenReturn(null);
		//when
		this.toTest.ensureRoleExists(_RoleName);
		//then
		verify(this.roleDao, times(1)).create(argThat(byName(_RoleName)));
	}

	@Test
	public void countRoles() {
		//given
		when(this.roleDao.count()).thenReturn(_RoleCount);
		//when
		final Long countRoles = this.toTest.countRoles();
		//then
		assertThat(countRoles, equalTo(_RoleCount));
		verify(this.roleDao, times(1)).count();
	}

	@Test
	public void toModel_WithPermissions() {
		//given
		this.permissionEntities.add(this.permissionEntity);
		when(this.roleEntity.getId()).thenReturn(_RoleId);
		when(this.roleEntity.getName()).thenReturn(_RoleName);
		when(this.roleEntity.getPermissions()).thenReturn(this.permissionEntities);
		when(this.permissionService.toModel(this.permissionEntity)).thenReturn(this.permissionModel);
		//when
		final RoleModel model = this.toTest.toModel(this.roleEntity);
		//then
		assertThat(model.getId(), equalTo(_RoleId));
		assertThat(model.getName(), equalTo(_RoleName));
		assertThat(model.getPermissions(), notNullValue());
		assertThat(model.getPermissions().size(), equalTo(1));
		assertThat(model.getPermissions().toArray()[0], equalTo(this.permissionModel));
	}

	@Test
	public void toModel_WithPermissions_Empty() {
		//given
		when(this.roleEntity.getId()).thenReturn(_RoleId);
		when(this.roleEntity.getName()).thenReturn(_RoleName);
		when(this.roleEntity.getPermissions()).thenReturn(this.permissionEntities);
		//when
		final RoleModel model = this.toTest.toModel(this.roleEntity);
		//then
		assertThat(model.getId(), equalTo(_RoleId));
		assertThat(model.getName(), equalTo(_RoleName));
		assertThat(model.getPermissions(), notNullValue());
		assertThat(model.getPermissions().size(), equalTo(0));
	}

	@Test
	public void toModel_WithPermissions_Null() {
		//given
		when(this.roleEntity.getId()).thenReturn(_RoleId);
		when(this.roleEntity.getName()).thenReturn(_RoleName);
		when(this.roleEntity.getPermissions()).thenReturn(null);
		//when
		final RoleModel model = this.toTest.toModel(this.roleEntity);
		//then
		assertThat(model.getId(), equalTo(_RoleId));
		assertThat(model.getName(), equalTo(_RoleName));
		assertThat(model.getPermissions(), notNullValue());
		assertThat(model.getPermissions().size(), equalTo(0));
	}

	@Test
	public void toEntity_WithPermissions() {
		//given
		this.permissionModels.add(this.permissionModel);
		when(this.roleModel.getId()).thenReturn(_RoleId);
		when(this.roleModel.getName()).thenReturn(_RoleName);
		when(this.roleModel.getPermissions()).thenReturn(this.permissionModels);
		when(this.permissionService.toEntity(this.permissionModel)).thenReturn(this.permissionEntity);
		//when
		final RoleEntity entity = this.toTest.toEntity(this.roleModel);
		//then
		assertThat(entity.getId(), equalTo(_RoleId));
		assertThat(entity.getName(), equalTo(_RoleName));
		assertThat(entity.getPermissions(), notNullValue());
		assertThat(entity.getPermissions().size(), equalTo(1));
		assertThat(entity.getPermissions().toArray()[0], equalTo(this.permissionEntity));
	}

	@Test
	public void toEntity_WithPermissions_Empty() {
		//given
		when(this.roleModel.getId()).thenReturn(_RoleId);
		when(this.roleModel.getName()).thenReturn(_RoleName);
		when(this.roleModel.getPermissions()).thenReturn(this.permissionModels);
		//when
		final RoleEntity entity = this.toTest.toEntity(this.roleModel);
		//then
		assertThat(entity.getId(), equalTo(_RoleId));
		assertThat(entity.getName(), equalTo(_RoleName));
		assertThat(entity.getPermissions(), notNullValue());
		assertThat(entity.getPermissions().size(), equalTo(0));
	}

	@Test
	public void toEntity_WithPermissions_Null() {
		//given
		when(this.roleModel.getId()).thenReturn(_RoleId);
		when(this.roleModel.getName()).thenReturn(_RoleName);
		when(this.roleModel.getPermissions()).thenReturn(null);
		//when
		final RoleEntity entity = this.toTest.toEntity(this.roleModel);
		//then
		assertThat(entity.getId(), equalTo(_RoleId));
		assertThat(entity.getName(), equalTo(_RoleName));
		assertThat(entity.getPermissions(), notNullValue());
		assertThat(entity.getPermissions().size(), equalTo(0));
	}

	private static RoleMatcher byName(final String name) {
		return new RoleMatcher(name);
	}

	private static class RoleMatcher implements ArgumentMatcher<RoleEntity> {
		private final String name;

		public RoleMatcher(final String name) {
			this.name = name;
		}

		@Override
		public boolean matches(RoleEntity role) {
			return this.name.equals(role.getName());
		}
	}
}
