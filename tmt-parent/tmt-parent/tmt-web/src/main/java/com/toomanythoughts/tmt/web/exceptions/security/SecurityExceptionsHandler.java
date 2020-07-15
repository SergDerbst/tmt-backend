package com.toomanythoughts.tmt.web.exceptions.security;

import org.apache.commons.lang3.exception.ContextedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.toomanythoughts.tmt.commons.exceptions.logic.impl.ResourceAlreadyExistsException;
import com.toomanythoughts.tmt.web.exceptions.TMTExceptionsHandler;

@ControllerAdvice
public class SecurityExceptionsHandler extends TMTExceptionsHandler {

	@ExceptionHandler({ AuthorizationFailedException.class })
	protected ResponseEntity<Object> authenticationFailed(final ContextedException e, final WebRequest request) {
		return super.handleException(e, request, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler({ ResourceAlreadyExistsException.class })
	protected ResponseEntity<Object> resourceAlreadyExists(final ContextedException e, final WebRequest request) {
		return super.handleException(e, request, HttpStatus.CONFLICT);
	}
}
