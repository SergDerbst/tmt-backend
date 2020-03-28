package com.toomanythoughts.tmt.web.layers.logic.auth.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toomanythoughts.tmt.web.layers.logic.auth.model.user.UserRoleModel;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.user.UserRolePermission;
import com.toomanythoughts.tmt.web.layers.persistence.daos.RoleDao;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.PermissionEntity;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.RoleEntity;

@Service
public class RoleService {

	@Autowired
	RoleDao roleDao;
	@Autowired
	PermissionService permissionService;

	public Long countRoles() {
		return this.roleDao.count();
	}

	public void ensureRoleExists(final String name) {
		final RoleEntity roleEntity = this.roleDao.getByName(name);
		if (roleEntity == null) {
			final RoleEntity role = new RoleEntity();
			role.setName(name);
			this.roleDao.create(role);
		}
	}

	public UserRoleModel findByName(String name) {
		final RoleEntity roleEntity = this.roleDao.getByName(name);
		if (roleEntity != null) {
			return this.toModel(roleEntity);
		} else {
			return null;
		}
	}

	public UserRoleModel findDefaultRole() {
		return this.findByName("Reader");
	}

	public UserRoleModel toModel(final RoleEntity entity) {
		final UserRoleModel model = new UserRoleModel();
		model.setId(entity.getId());
		model.setName(entity.getName());
		final Set<UserRolePermission> permissions = new HashSet<>();
		if (entity.getPermissions() != null && !entity.getPermissions().isEmpty()) {
			for (final PermissionEntity permissionEntity : entity.getPermissions()) {
				permissions.add(this.permissionService.toModel(permissionEntity));
			}
		}
		model.setPermissions(permissions);
		return model;
	}

	public RoleEntity toEntity(final UserRoleModel model) {
		final RoleEntity entity = new RoleEntity();
		entity.setId(model.getId());
		entity.setName(model.getName());
		final Set<PermissionEntity> permissions = new HashSet<>();
		if (model.getPermissions() != null && !model.getPermissions().isEmpty()) {
			for (final UserRolePermission permission : model.getPermissions()) {
				permissions.add(this.permissionService.toEntity(permission));
			}
		}
		entity.setPermissions(permissions);
		return entity;
	}
}
