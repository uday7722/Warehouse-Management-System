package com.jsp.warehouse.responsedto;

import java.time.LocalDate;
import java.util.List;

import com.jsp.warehouse.enums.MaterialType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InventoryResponse {
	
	private int productId;
	private String productTitle;
	private double weightInKg;
	private int quantity;
	List<MaterialType> materialTypes;
	private LocalDate restockedAt; 
	private int sellerId;

}
