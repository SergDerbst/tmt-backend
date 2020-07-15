package com.toomanythoughts.tmt.web.logic.security.authorization.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toomanythoughts.tmt.web.logic.security.authorization.model.PermissionModel;
import com.toomanythoughts.tmt.web.logic.security.authorization.model.RoleModel;
import com.toomanythoughts.tmt.web.persistence.daos.security.RoleDao;
import com.toomanythoughts.tmt.web.persistence.entities.security.PermissionEntity;
import com.toomanythoughts.tmt.web.persistence.entities.security.RoleEntity;

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

	public RoleModel findByName(String name) {
		final RoleEntity roleEntity = this.roleDao.getByName(name);
		if (roleEntity != null) {
			return this.toModel(roleEntity);
		} else {
			return null;
		}
	}

	public RoleModel findDefaultRole() {
		return this.findByName("Reader");
	}

	public Set<RoleModel> roles(Set<RoleEntity> roles) {
		final Set<RoleModel> models = new HashSet<>();
		for (final RoleEntity entity : roles) {
			models.add(this.toModel(entity));
		}
		return models;
	}

	public RoleModel toModel(final RoleEntity entity) {
		final RoleModel model = new RoleModel();
		model.setId(entity.getId());
		model.setName(entity.getName());
		final Set<PermissionModel> permissions = new HashSet<>();
		if (entity.getPermissions() != null && !entity.getPermissions().isEmpty()) {
			for (final PermissionEntity permissionEntity : entity.getPermissions()) {
				permissions.add(this.permissionService.toModel(permissionEntity));
			}
		}
		model.setPermissions(permissions);
		return model;
	}

	public RoleEntity toEntity(final RoleModel model) {
		final RoleEntity entity = new RoleEntity();
		entity.setId(model.getId());
		entity.setName(model.getName());
		final Set<PermissionEntity> permissions = new HashSet<>();
		if (model.getPermissions() != null && !model.getPermissions().isEmpty()) {
			for (final PermissionModel permission : model.getPermissions()) {
				permissions.add(this.permissionService.toEntity(permission));
			}
		}
		entity.setPermissions(permissions);
		return entity;
	}
}
