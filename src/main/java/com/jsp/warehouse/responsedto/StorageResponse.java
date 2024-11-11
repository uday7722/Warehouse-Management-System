package com.jsp.warehouse.responsedto;

import java.util.List;

import com.jsp.warehouse.enums.MaterialType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StorageResponse {
	
	private int storageId;
	private String blockName;
	private String section;
	private double lengthInMeters;
	private double breadthInMeters;
	private double heightInMeters;
	private double capacityWeightInKg;
    private double maxAdditionalWeightInKg;
    private double availableAreaInMetres;
    
    List<MaterialType> materialTypes;

}
