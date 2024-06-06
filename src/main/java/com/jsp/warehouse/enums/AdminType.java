package com.jsp.warehouse.enums;

import java.util.List;

public enum AdminType {

	SUPER_ADMIN(List.of(Privilege.CREATE_ADMIN,Privilege.CREATE_WAREHOUSE,Privilege.CREATE_STORAGE,Privilege.CREATE_ADDRESS,
			Privilege.READ
			,Privilege.UPDATE_ADMIN,Privilege.UPDATE_WAREHOUSE,Privilege.UPDATE_STORAGE,Privilege.UPDATE_ADDRESS,
			Privilege.DELETE_ADMIN,Privilege.DELETE_WAREHOUSE,Privilege.DELETE_STORAGE,Privilege.DELETE_ADDRESS)),

	ADMIN(List.of(Privilege.CREATE_STORAGE,Privilege.READ,Privilege.UPDATE_ADMIN,Privilege.UPDATE_STORAGE,Privilege.DELETE_STORAGE));

	private List<Privilege> privileges;

	private AdminType(List<Privilege> privileges) {
		this.privileges=privileges;
	}

	public List<Privilege> getPrivileges(){
		return this.privileges;
	}



}
