package com.capgemini.exceptionHandler;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.capgemini.exception.NotValidPasswordException;
import com.capgemini.exception.NotValidRoleException;
import com.capgemini.exception.UserNotFoundException;
import com.capgemini.model.ExceptionDetails;

@ControllerAdvice
public class UserExceptionHandler {
	
	@ExceptionHandler(value = UserNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> userNotFoundException(UserNotFoundException ex, WebRequest request)
	{
		ExceptionDetails exceptionDetails = new ExceptionDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = NotValidPasswordException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> notValidPasswordException(NotValidPasswordException ex, WebRequest request)
	{
		ExceptionDetails exceptionDetails = new ExceptionDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = NotValidRoleException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> loginFailedDueToWrongRole(NotValidRoleException ex, WebRequest request) 
	{
		ExceptionDetails exceptionDetails = new ExceptionDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	

}
