package com.sulphur.admin;

import java.util.Map;

public interface AdminDaoImp {
	public void update(AdminVO adminVO);
	public Map checkLogin(String name, String pass);
}
