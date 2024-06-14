package com.jsp.warehouse.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientRequest {
	
	private String buisnessName;
	private String email;
	private long contactNumber;

}
