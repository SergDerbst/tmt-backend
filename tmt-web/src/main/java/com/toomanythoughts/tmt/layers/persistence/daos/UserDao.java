package com.toomanythoughts.tmt.layers.persistence.daos;

import com.toomanythoughts.tmt.layers.persistence.CrudDao;
import com.toomanythoughts.tmt.layers.persistence.entities.impl.UserEntity;

public interface UserDao extends CrudDao<UserEntity, Integer> {

	UserEntity getByUsername(String username);

}
