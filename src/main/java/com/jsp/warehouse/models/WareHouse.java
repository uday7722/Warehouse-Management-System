package com.jsp.warehouse.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WareHouse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int wareHouseId;
	private String name;
	
//	@OneToOne(mappedBy = "warehouse")
//	private Admin admin;

}
