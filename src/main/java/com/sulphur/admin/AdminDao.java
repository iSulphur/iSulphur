package com.sulphur.admin;


public interface AdminDao {
	public int update(Admin admin);
	public Admin checkLogin(String name, String pass);
	public void test();
}
