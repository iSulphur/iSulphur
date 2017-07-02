package com.sulphur.admin;


public interface AdminDao {
	public Password checkLogin(String name, String pass);
	public int updatePass(String name, String pass);
	
	public int updateInfo(Admin admin);
	public Admin getInfoByID(String name);
}
