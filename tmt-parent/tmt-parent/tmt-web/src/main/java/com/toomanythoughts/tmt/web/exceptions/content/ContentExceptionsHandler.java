package com.toomanythoughts.tmt.web.exceptions.content;

import org.apache.commons.lang3.exception.ContextedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.toomanythoughts.tmt.commons.exceptions.logic.impl.FormDataInvalidException;
import com.toomanythoughts.tmt.web.exceptions.TMTExceptionsHandler;

public class ContentExceptionsHandler extends TMTExceptionsHandler {

	@ExceptionHandler({ ContentNotFoundException.class })
	protected ResponseEntity<Object> contentNotFound(final ContextedException e, final WebRequest request) {
		return super.handleException(e, request, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ FormDataInvalidException.class })
	protected ResponseEntity<Object> formDataInvalid(final ContextedException e, final WebRequest request) {
		return super.handleException(e, request, HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
