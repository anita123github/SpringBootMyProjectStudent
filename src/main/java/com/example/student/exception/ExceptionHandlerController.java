package com.example.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.student.exceptionResponse.ExceptionResponse;

@RestControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(NoSuchElementFoundException .class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public ExceptionResponse HandleException(NoSuchElementFoundException exception) {
		ExceptionResponse exceptionResponse=new ExceptionResponse();
		exceptionResponse.setErrorCode("ERROR_STUDENT");
		exceptionResponse.setErrorMessage(exception.getMessage());
		return exceptionResponse;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponse handExceptionResponse (Exception exception ) {
		ExceptionResponse exceptionResponse=new ExceptionResponse();
		exceptionResponse.setErrorCode("ERROR_STUDENT");
		exceptionResponse.setErrorMessage(exception.getMessage());
		return exceptionResponse;
		
	}
}
