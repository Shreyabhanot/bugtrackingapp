package com.capgemini.exceptionHandler;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.capgemini.exception.NoSuchProjectException;
import com.capgemini.model.ExceptionDetails;

@ControllerAdvice
public class ProjectExceptionHandler {
	
	@ExceptionHandler(value = NoSuchProjectException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> resouceNotFoundException(NoSuchProjectException ex, WebRequest request)
	{
		ExceptionDetails exceptionDetails = new ExceptionDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
	}

}
