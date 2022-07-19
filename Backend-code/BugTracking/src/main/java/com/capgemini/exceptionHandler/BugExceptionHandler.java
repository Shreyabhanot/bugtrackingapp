package com.capgemini.exceptionHandler;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.capgemini.exception.NoSuchBugFoundException;
import com.capgemini.exception.NotValidEndDateException;
import com.capgemini.model.ExceptionDetails;

@ControllerAdvice
public class BugExceptionHandler {
	
	@ExceptionHandler(value = NoSuchBugFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleNoSuchBugFound(NoSuchBugFoundException ex, WebRequest request) {
		ExceptionDetails exceptionDetails = new ExceptionDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = NotValidEndDateException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR) 
	public ResponseEntity<Object> handleNotValidEndDateException(NotValidEndDateException ex, WebRequest request){
		ExceptionDetails exceptionDetails = new ExceptionDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
