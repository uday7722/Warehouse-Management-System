package com.jsp.warehouse.responsedto;


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
public class AllStorageResponse {
	
	 private double lengthInMeters;
		private double breadthInMeters;
		private double heightInMeters;
		private double capacityWeightInKg;

}
