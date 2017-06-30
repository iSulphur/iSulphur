package com.sulphur.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImp implements AdminService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int update(AdminVO adminVO) {
		// TODO Auto-generated method stub
		String sql="update admin set password=? where username=?";
		return jdbcTemplate.update(sql,adminVO.getPass(), adminVO.getName());
	}

	public AdminVO checkLogin(String name, String pass) {
		// TODO Auto-generated method stub
		String sql = "select * from admin where username=? and password=?";
		List<AdminVO> admins = jdbcTemplate.query(sql, new Object[] {name, pass}, new RowMapper<AdminVO>(){
			@Override
			public AdminVO mapRow(ResultSet rs, int num) throws SQLException{
				AdminVO a = new AdminVO();
				a.setName(rs.getString("username"));
				a.setPass(rs.getString("password"));
				return a;
			}
		});
		AdminVO admin = null;
		if (admins != null && admins.size() > 0){
			admin = admins.get(0);
		}
		return admin;
	}
}
