package com.toomanythoughts.tmt.web.layers.logic.auth.services.authorization;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.toomanythoughts.tmt.web.logic.auth.authorization.model.PermissionModel;
import com.toomanythoughts.tmt.web.logic.auth.authorization.services.PermissionService;
import com.toomanythoughts.tmt.web.persistence.entities.auth.PermissionEntity;

@RunWith(SpringRunner.class)
public class PermissionService_UnitTest {

	private static final int _PermissionId = 666;
	private static final String _PermissionName = "allowed to kiss arse";

	@TestConfiguration
	static class PermissionServiceUnitTestConfiguration {
		@Bean
		public PermissionService toTest() {
			return new PermissionService();
		}
	}

	@Autowired
	PermissionService toTest;
	@MockBean
	PermissionEntity permissionEntity;
	@MockBean
	PermissionModel permissionModel;
	@MockBean
	Map<String, Object> configuration;

	@Test
	public void toEntity() {
		//given
		when(this.permissionModel.getId()).thenReturn(_PermissionId);
		when(this.permissionModel.getName()).thenReturn(_PermissionName);
		when(this.permissionModel.getConfiguration()).thenReturn(this.configuration);
		//when
		final PermissionEntity entity = this.toTest.toEntity(this.permissionModel);
		//then
		assertThat(entity.getId(), equalTo(_PermissionId));
		assertThat(entity.getName(), equalTo(_PermissionName));
		assertThat(entity.getConfiguration(), equalTo(this.configuration));
	}

	@Test
	public void toModel() {
		//given
		when(this.permissionEntity.getId()).thenReturn(_PermissionId);
		when(this.permissionEntity.getName()).thenReturn(_PermissionName);
		when(this.permissionEntity.getConfiguration()).thenReturn(this.configuration);
		//when
		final PermissionModel model = this.toTest.toModel(this.permissionEntity);
		//then
		assertThat(model.getId(), equalTo(_PermissionId));
		assertThat(model.getName(), equalTo(_PermissionName));
		assertThat(model.getConfiguration(), equalTo(this.configuration));
	}
}
