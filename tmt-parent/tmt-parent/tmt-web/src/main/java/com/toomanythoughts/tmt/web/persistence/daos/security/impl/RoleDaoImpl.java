package com.toomanythoughts.tmt.web.persistence.daos.security.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toomanythoughts.tmt.commons.layers.persistence.BaseCrudDaoService;
import com.toomanythoughts.tmt.web.persistence.daos.security.RoleDao;
import com.toomanythoughts.tmt.web.persistence.entities.security.RoleEntity;
import com.toomanythoughts.tmt.web.persistence.repositories.security.RoleRepository;

@Repository
@Transactional
public class RoleDaoImpl extends BaseCrudDaoService<RoleRepository, RoleEntity> implements RoleDao {

	@Autowired
	private RoleRepository repository;

	@Override
	public RoleRepository getRepository() {
		return this.repository;
	}

	@Override
	public RoleEntity getByName(String name) {
		return this.repository.getByName(name);
	}
}
