package com.sulphur.admin;

public class Admin {
	private String adminID;
	private String adminName;
	private String adminPhone;
	
	public Admin(){
		
	}
	
	public Admin(String id, String name, String phone){
		adminID = id;
		adminName = name;
		adminPhone = phone;
	}
	
	public String getAdminID() {
		return adminID;
	}
	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminPhone() {
		return adminPhone;
	}
	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}
	

	
	
}
