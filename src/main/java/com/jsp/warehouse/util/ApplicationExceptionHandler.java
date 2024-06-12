package com.jsp.warehouse.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.warehouse.exception.AddressNotFoundByIdException;
import com.jsp.warehouse.exception.AdminNotFoundByIdException;
import com.jsp.warehouse.exception.IllegalOperationException;
import com.jsp.warehouse.exception.WareHouseNotFoundByIdException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	
	public ResponseEntity<ErrorStructure<String>> errorResponse(HttpStatus status,String message,String rootCause){
		
		return ResponseEntity.status(status).body(new ErrorStructure<String>()
				.setStatus(status.value())
				.setMessage(message)
				.setRootCause(rootCause));
	}
	
	
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<Map<String, String>>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception){
		
		List<ObjectError> allErrors = exception.getAllErrors();
		Map<String, String> map=new HashMap<>();
		 allErrors.forEach(error->{
			FieldError fieldError=(FieldError) error;
			String defaultMessage = fieldError.getDefaultMessage();
			String field = fieldError.getField();
			map.put(field, defaultMessage);
		});
		 
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorStructure<Map<String, String>>()
				 .setStatus(HttpStatus.BAD_REQUEST.value())
				 .setMessage("Invalid input")
				 .setRootCause(map));
		
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> hadlerIllegalOperationExcepetion(IllegalOperationException exception){
		return errorResponse(HttpStatus.FORBIDDEN, exception.getMessage(),"Super admin is already present" ); 
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handlerAddressNotFoundByIdException(AddressNotFoundByIdException exception){
		return errorResponse(HttpStatus.NOT_FOUND, exception.getMessage(), "Give the credentials which are present");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handlerAdminNotFoundByIdException(AdminNotFoundByIdException exception){
		return errorResponse(HttpStatus.NOT_FOUND, exception.getMessage(), "Give the credentials which are present");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handlerWarehouseNotFoundByIdException(WareHouseNotFoundByIdException exception){
		return errorResponse(HttpStatus.NOT_FOUND, exception.getMessage(), "Give the credentials which are present");
	}

}