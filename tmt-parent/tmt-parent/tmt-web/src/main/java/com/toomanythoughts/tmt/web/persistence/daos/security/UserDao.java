package com.toomanythoughts.tmt.web.persistence.daos.security;

import com.toomanythoughts.tmt.commons.layers.persistence.CrudDao;
import com.toomanythoughts.tmt.web.persistence.entities.security.UserEntity;

public interface UserDao extends CrudDao<UserEntity, Integer> {

	UserEntity getById(Integer id);

	UserEntity byUsername(String username);

	UserEntity byEmail(String email);

}
