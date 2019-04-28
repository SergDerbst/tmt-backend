package com.toomanythoughts.tmt.layers.persistence.daos;

import org.springframework.security.core.userdetails.UserDetails;

import com.toomanythoughts.tmt.layers.persistence.CrudDao;
import com.toomanythoughts.tmt.layers.persistence.entities.impl.User;

public interface UserDao extends CrudDao<User, Integer> {

	UserDetails loadByUsername(String username);

}
