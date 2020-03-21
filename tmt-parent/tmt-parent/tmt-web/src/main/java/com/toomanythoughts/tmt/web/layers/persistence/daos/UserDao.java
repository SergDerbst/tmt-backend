package com.toomanythoughts.tmt.web.layers.persistence.daos;

import com.toomanythoughts.tmt.commons.layers.persistence.CrudDao;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.UserEntity;

public interface UserDao extends CrudDao<UserEntity, Integer> {

	UserEntity getByUsername(String username);

}
