package com.sulphur.admin;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AdminDao implements AdminDaoImp {
	
	@Resource
	private JdbcTemplate jdbcTemplate;

	public void update(AdminVO adminVO) {
		// TODO Auto-generated method stub
		String sql="update admin set password=? where username=?";
		jdbcTemplate.update(sql,adminVO.getPass(), adminVO.getName());
	}

	public Map checkLogin(String name, String pass) {
		// TODO Auto-generated method stub
		String sql = "select * from admin where username=? and password=?";
		try {
			return this.getJdbcTemplate().queryForMap(sql, name, pass);
		} catch (Exception e) {
			return null;
		}
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

}
