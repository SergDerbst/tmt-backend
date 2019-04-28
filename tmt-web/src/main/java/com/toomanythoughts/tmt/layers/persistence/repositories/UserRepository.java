package com.toomanythoughts.tmt.layers.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toomanythoughts.tmt.layers.persistence.entities.impl.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	UserEntity getByUsername(String username);

}
