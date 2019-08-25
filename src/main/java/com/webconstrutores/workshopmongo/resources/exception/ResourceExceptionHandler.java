package com.webconstrutores.workshopmongo.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.webconstrutores.workshopmongo.services.exception.ObjecctNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjecctNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjecctNotFoundException e, HttpServletRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError error = new StandardError(
				System.currentTimeMillis(),
				status.value(),
				"Não encontrado",
				e.getMessage(),
				request.getRequestURI());
		
		return ResponseEntity.status(status).body(error);
	}
}
