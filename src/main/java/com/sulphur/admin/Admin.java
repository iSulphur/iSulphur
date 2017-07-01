package com.sulphur.admin;

public class Admin {
	private String name;
	private String pass;
	
	public Admin(){
		
	}
	
	public Admin(String name, String pass){
		super();
		this.name = name;
		this.pass = pass;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setPass(String pass){
		this.pass = pass;
	}
	public String getPass(){
		return this.pass;
	}
	
}
