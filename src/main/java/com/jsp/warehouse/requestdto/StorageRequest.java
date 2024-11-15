package com.jsp.warehouse.requestdto;

import java.util.List;

import com.jsp.warehouse.enums.MaterialType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StorageRequest {
	
	private String blockName;
	private String section;
	
	List<MaterialType> materialType;

}
