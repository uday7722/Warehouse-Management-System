package com.jsp.warehouse.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientNotFoundByEmailException extends RuntimeException{
	
	private String message;

}
