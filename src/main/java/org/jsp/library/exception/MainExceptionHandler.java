package org.jsp.library.exception;

import java.util.InputMismatchException;

import org.jsp.library.helper.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class MainExceptionHandler {
	@ExceptionHandler(ShouldNotRepeatException.class)
	public ResponseEntity<ResponseStructure<String>> shouldNotrepeat(ShouldNotRepeatException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData(exception.getMessage());
		structure.setMessage("There is an Exception");
		structure.setStatus(HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<String>> hibernateValidation(ConstraintViolationException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData(exception.getMessage());
		structure.setMessage("There is an Exception");
		structure.setStatus(HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> notFound(NotFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData(exception.getMessage());
		structure.setMessage("There is an Exception");
		structure.setStatus(HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InputMismatchException.class)
	public ResponseEntity<ResponseStructure<String>> notFound(InputMismatchException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData(exception.getMessage());
		structure.setMessage("There is an Exception");
		structure.setStatus(HttpStatus.NOT_ACCEPTABLE.value());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_ACCEPTABLE);
	}
	@ExceptionHandler(VerificationPendingException.class)
	public ResponseEntity<ResponseStructure<String>> notVerified(VerificationPendingException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData(exception.getMessage());
		structure.setMessage("There is an Exception");
		structure.setStatus(HttpStatus.UNAUTHORIZED.value());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_ACCEPTABLE);
	}
}
