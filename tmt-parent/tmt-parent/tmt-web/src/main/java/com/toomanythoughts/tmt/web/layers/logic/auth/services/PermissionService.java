package com.toomanythoughts.tmt.web.layers.logic.auth.services;

import org.springframework.stereotype.Service;

import com.toomanythoughts.tmt.web.layers.logic.auth.model.user.UserRolePermission;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.PermissionEntity;

@Service
public class PermissionService {

	public UserRolePermission toModel(final PermissionEntity entity) {
		final UserRolePermission model = new UserRolePermission();
		model.setId(entity.getId());
		model.setName(entity.getName());
		model.setConfiguration(entity.getConfiguration());
		return model;
	}

	public PermissionEntity toEntity(final UserRolePermission model) {
		final PermissionEntity entity = new PermissionEntity();
		entity.setId(model.getId());
		entity.setName(entity.getName());
		entity.setConfiguration(model.getConfiguration());
		return entity;
	}
}
