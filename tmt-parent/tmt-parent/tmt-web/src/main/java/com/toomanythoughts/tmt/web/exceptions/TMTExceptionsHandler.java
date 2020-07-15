package com.toomanythoughts.tmt.web.exceptions;

import org.apache.commons.lang3.exception.ContextedException;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public abstract class TMTExceptionsHandler extends ResponseEntityExceptionHandler {

	protected ResponseEntity<Object> handleException(final ContextedException e, final WebRequest request, final HttpStatus status) {
		return super.handleExceptionInternal(e, this.setErrorProperties(e, this.errorResponseModel(e, request)), new HttpHeaders(), status, request);
	}

	protected ErrorResponseModel errorResponseModel(final ContextedException e, WebRequest request) {
		final ErrorResponseModel errorModel = new ErrorResponseModel();
		errorModel.setName(this.clearExceptionName(e.getClass().getSimpleName()));
		return errorModel;
	}

	private String clearExceptionName(String simpleName) {
		return simpleName.replace("Exception", "");
	}

	private ErrorResponseModel setErrorProperties(ContextedException e, final ErrorResponseModel errorModel) {
		for (final Pair<String, Object> errorProperty : e.getContextEntries()) {
			errorModel.getProperties().put(errorProperty.getKey(), errorProperty.getValue());
		}
		return errorModel;
	}
}
