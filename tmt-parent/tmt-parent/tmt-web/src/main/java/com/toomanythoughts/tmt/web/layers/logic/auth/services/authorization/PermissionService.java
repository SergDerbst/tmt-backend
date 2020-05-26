package com.toomanythoughts.tmt.web.layers.logic.auth.services.authorization;

import org.springframework.stereotype.Service;

import com.toomanythoughts.tmt.web.layers.logic.auth.model.authorization.PermissionModel;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.PermissionEntity;

@Service
public class PermissionService {

	public PermissionModel toModel(final PermissionEntity entity) {
		final PermissionModel model = new PermissionModel();
		model.setId(entity.getId());
		model.setName(entity.getName());
		model.setConfiguration(entity.getConfiguration());
		return model;
	}

	public PermissionEntity toEntity(final PermissionModel model) {
		final PermissionEntity entity = new PermissionEntity();
		entity.setId(model.getId());
		entity.setName(model.getName());
		entity.setConfiguration(model.getConfiguration());
		return entity;
	}
}
