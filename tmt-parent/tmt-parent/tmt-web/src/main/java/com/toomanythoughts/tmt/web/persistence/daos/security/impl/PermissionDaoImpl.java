package com.toomanythoughts.tmt.web.persistence.daos.security.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toomanythoughts.tmt.commons.layers.persistence.BaseCrudDao;
import com.toomanythoughts.tmt.web.persistence.daos.security.PermissionDao;
import com.toomanythoughts.tmt.web.persistence.entities.security.PermissionEntity;
import com.toomanythoughts.tmt.web.persistence.repositories.security.PermissionRepository;

@Repository
@Transactional
public class PermissionDaoImpl extends BaseCrudDao<PermissionRepository, PermissionEntity> implements PermissionDao {

	@Autowired
	PermissionRepository repository;

	@Override
	public PermissionRepository getRepository() {
		return this.repository;
	}

}
