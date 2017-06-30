package com.sulphur.admin;


public interface AdminService {
	public int update(AdminVO adminVO);
	public AdminVO checkLogin(String name, String pass);
}
