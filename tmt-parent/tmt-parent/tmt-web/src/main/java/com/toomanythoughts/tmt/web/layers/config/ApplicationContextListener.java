package com.toomanythoughts.tmt.web.layers.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.toomanythoughts.tmt.commons.exceptions.config.impl.RolesNotConfiguredRuntimeException;
import com.toomanythoughts.tmt.web.layers.logic.auth.services.RoleService;

@Component
public class ApplicationContextListener implements ApplicationListener<ContextRefreshedEvent> {

	private static final int Number_Of_Roles = 8;

	@Autowired
	RoleService roleService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		this.roleService.ensureRoleExists("Reader");
		this.roleService.ensureRoleExists("Voter");
		this.roleService.ensureRoleExists("Translator");
		this.roleService.ensureRoleExists("Author");
		this.roleService.ensureRoleExists("Moderator");
		this.roleService.ensureRoleExists("Admin");
		this.roleService.ensureRoleExists("Developer");
		this.roleService.ensureRoleExists("God");

		if (this.roleService.countRoles() != Number_Of_Roles) {
			throw RolesNotConfiguredRuntimeException.prepare(Number_Of_Roles);
		}
	}
}
