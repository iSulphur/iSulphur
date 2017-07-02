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

	public int updatePass(String id, String pass) {
		String sql="update password set password=? where id=? and type=0";
		return jdbcTemplate.update(sql,pass,id);
	}
	@Override
	public Password checkLogin(String id, String pass) {
		String sql = "select * from password where id=? and password=? and type=0";
		List<Password> res = jdbcTemplate.query(sql, new Object[] {id, pass}, new RowMapper<Password>(){
			@Override
			public Password mapRow(ResultSet rs, int num) throws SQLException{
				Password a = new Password();
				a.setId(rs.getString("id"));
				a.setPassword(rs.getString("password"));
				a.setType(rs.getString("type"));
				return a;
			}
		});
		Password r = null;
		if (res != null && res.size() > 0){
			r = res.get(0);
		}
		return r;
	}

	@Override
	public int updateInfo(Admin admin) {
		String sql="update admin set admin_name=?,admin_phone=? where admin_id=?";
		return jdbcTemplate.update(sql,admin.getAdminName(), admin.getAdminPhone(), admin.getAdminID());
	}

	@Override
	public Admin getInfoByID(String id) {
		String sql = "select * from admin where admin_id=?";
		List<Admin> res = jdbcTemplate.query(sql, new Object[] {id}, new RowMapper<Admin>(){
			@Override
			public Admin mapRow(ResultSet rs, int num) throws SQLException{
				Admin a = new Admin();
				a.setAdminID(rs.getString("admin_id"));
				a.setAdminName(rs.getString("admin_name"));
				a.setAdminPhone(rs.getString("admin_phone"));
				return a;
			}
		});
		Admin r = null;
		if (res != null && res.size() > 0){
			r = res.get(0);
		}
		return r;
	}
}
