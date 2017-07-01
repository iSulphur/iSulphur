package com.sulphur.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class AdminDaoImp implements AdminDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int update(Admin admin) {
		String sql="update admin set password=? where username=?";
		return jdbcTemplate.update(sql,admin.getPass(), admin.getName());
	}

	public Admin checkLogin(String name, String pass) {
		String sql = "select * from admin where username=? and password=?";
		List<Admin> admins = jdbcTemplate.query(sql, new Object[] {name, pass}, new RowMapper<Admin>(){
			@Override
			public Admin mapRow(ResultSet rs, int num) throws SQLException{
				Admin a = new Admin();
				a.setName(rs.getString("username"));
				a.setPass(rs.getString("password"));
				return a;
			}
		});
		Admin admin = null;
		if (admins != null && admins.size() > 0){
			admin = admins.get(0);
		}
		return admin;
	}
	public void test(){
		System.out.println(jdbcTemplate);
	}
}
