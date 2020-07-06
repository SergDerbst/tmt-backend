package com.toomanythoughts.tmt.web.logic.auth;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class AuditorService implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return null;
	}
}
