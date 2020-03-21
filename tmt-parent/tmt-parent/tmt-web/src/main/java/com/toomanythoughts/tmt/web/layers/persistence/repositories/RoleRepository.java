package com.toomanythoughts.tmt.web.layers.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

	@Query(value = "select r from RoleEntity r where r.name = ?1")
	RoleEntity getByName(final String name);
}
