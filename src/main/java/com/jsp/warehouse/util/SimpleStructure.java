package com.jsp.warehouse.util;


public class SimpleStructure <T>{
	
	private int status;
	private String messsage;
	public int getStatus() {
		return status;
	}
	public String getMesssage() {
		return messsage;
	}
	public SimpleStructure <T> setStatus(int status) {
		this.status = status;
		return this;
	}
	public SimpleStructure <T> setMesssage(String messsage) {
		this.messsage = messsage;
		return this;
	}
	
	
	

}
