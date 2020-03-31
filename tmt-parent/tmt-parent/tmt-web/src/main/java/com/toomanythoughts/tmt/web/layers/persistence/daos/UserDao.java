package com.toomanythoughts.tmt.web.layers.persistence.daos;

import com.toomanythoughts.tmt.commons.layers.persistence.CrudDao;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.UserEntity;

public interface UserDao extends CrudDao<UserEntity, Integer> {

	UserEntity getById(Integer id);

	UserEntity byUsername(String username);

	UserEntity byEmail(String email);

}
