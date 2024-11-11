package com.jsp.warehouse.models;

import java.util.List;

import com.jsp.warehouse.enums.MaterialType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Storage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int storageId;
	private String blockName;
	private String section;
	
    private double maxAdditionalWeightInKg;
    private double availableAreaInMetres;
    private int sellerId;
     
    @Enumerated(EnumType.STRING)
    List<MaterialType> materialType;
    
	
    @ManyToOne
    private WareHouse wareHouse;
    
    @ManyToOne
    private StorageType storageType;
	
	
	

}
