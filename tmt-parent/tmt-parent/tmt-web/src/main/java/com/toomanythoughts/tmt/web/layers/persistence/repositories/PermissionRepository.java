package com.toomanythoughts.tmt.web.layers.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.PermissionEntity;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer> {

}
