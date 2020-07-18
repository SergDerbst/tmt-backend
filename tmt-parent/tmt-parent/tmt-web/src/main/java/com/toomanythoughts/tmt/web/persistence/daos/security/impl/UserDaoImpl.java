package com.toomanythoughts.tmt.web.persistence.daos.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.toomanythoughts.tmt.commons.layers.persistence.BaseCrudDao;
import com.toomanythoughts.tmt.web.persistence.daos.security.UserDao;
import com.toomanythoughts.tmt.web.persistence.entities.security.UserEntity;
import com.toomanythoughts.tmt.web.persistence.repositories.security.UserRepository;

@Repository
@Transactional
public class UserDaoImpl extends BaseCrudDao<UserRepository, UserEntity> implements UserDao {

	@Autowired
	UserRepository repository;

	@Override
	public UserRepository getRepository() {
		return this.repository;
	}

	@Override
	public UserEntity getById(Integer id) {
		return this.repository.getById(id);
	}

	@Override
	public UserEntity getByUsername(final String username) {
		return this.repository.getByUsername(username);
	}

	@Override
	public UserEntity byEmail(String email) {
		return this.repository.getByEmail(email);
	}
}
