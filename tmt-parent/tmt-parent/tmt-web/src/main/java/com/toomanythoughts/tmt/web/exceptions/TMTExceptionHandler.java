package com.toomanythoughts.tmt.web.exceptions;

import org.apache.commons.lang3.exception.ContextedException;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.toomanythoughts.tmt.commons.exceptions.logic.impl.ResourceAlreadyExistsException;

@ControllerAdvice
public class TMTExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({
		ResourceAlreadyExistsException.class
	})
	protected ResponseEntity<Object> resourceAlreadyExists(ContextedException e, WebRequest request) {
		final ErrorResponseModel errorModel = new ErrorResponseModel();
		errorModel.setName(this.clearExceptionName(e.getClass().getSimpleName()));
		for (final Pair<String, Object> errorProperty : e.getContextEntries()) {
			errorModel.getProperties().put(errorProperty.getKey(), errorProperty.getValue());
		}
		return super.handleExceptionInternal(e, errorModel, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	private String clearExceptionName(String simpleName) {
		return simpleName.replace("Exception", "");
	}
}
