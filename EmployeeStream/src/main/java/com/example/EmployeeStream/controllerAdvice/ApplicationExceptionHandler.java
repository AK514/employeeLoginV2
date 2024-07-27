package com.example.EmployeeStream.controllerAdvice;

//import com.example.ExceptionValidation.exception.UserNotFoundException;
import com.example.EmployeeStream.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleInvalidArgument(MethodArgumentNotValidException ex){
		
		Map<String,String> errorMap=new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error ->
		{
			errorMap.put(error.getField(), error.getDefaultMessage());
		}
				
		);
		return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Map<String,String>> handleUserNotFoundException(UserNotFoundException ex){
		Map<String, String> errorMap=new HashMap<>();
		errorMap.put("errorMessage", ex.getMessage());
		 return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
	}
	

}
