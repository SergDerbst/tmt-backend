package com.toomanythoughts.tmt.web.persistence.repositories.security;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toomanythoughts.tmt.web.persistence.entities.security.PermissionEntity;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer> {

}
