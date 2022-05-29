package com.xoriant.delivery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandeler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserInputException.class)
	public ResponseEntity<String> userInputException(UserInputException exception) {
		return new ResponseEntity<String>("Please check Input Fileds !", HttpStatus.BAD_REQUEST);
	}
}
