package com.jsp.warehouse.requestdto;

import java.time.LocalDate;
import java.util.List;

import com.jsp.warehouse.enums.MaterialType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryRequest {
	
	private int productId;
	private String productTitle;
	private double lengthInMeters;
	private double breadthInMeters;
	private double heightInMeters;
	private double weightInKg;
	private int quantity;
	List<MaterialType> materialTypes;
	private LocalDate restockedAt; 
	private int sellerId;

}
