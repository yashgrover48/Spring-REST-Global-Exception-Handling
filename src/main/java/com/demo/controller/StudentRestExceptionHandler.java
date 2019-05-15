package com.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.rest.StudentErrorResponse;
import com.demo.rest.StudentNotFoundException;

@ControllerAdvice
public class StudentRestExceptionHandler {
		
	// add exception handling code here
	
	// Add an exception handler using @ExceptionHandler
	
		@ExceptionHandler
		public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
			
			// create a StudentErrorResponse
			
			StudentErrorResponse error= new StudentErrorResponse();
			error.setStatus(HttpStatus.NOT_FOUND.value());
			error.setMessage(exc.getMessage());
			error.setTimeStamp(System.currentTimeMillis());
			
			// return ResponseEntity
			
			return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
		}
		
		// Add another exeption handler for any other exception
		
		@ExceptionHandler
		public ResponseEntity<StudentErrorResponse> handleException(Exception exc){ 
			// create a StudentErrorResponse
			
			StudentErrorResponse error= new StudentErrorResponse();
			error.setStatus(HttpStatus.BAD_REQUEST.value());
			error.setMessage(exc.getMessage());
			error.setTimeStamp(System.currentTimeMillis());
					
			// return ResponseEntity
					
			return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		
		}
}
