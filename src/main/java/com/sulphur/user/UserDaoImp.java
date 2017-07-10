package com.sulphur.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.sulphur.admin.Password;


@Service
public class UserDaoImp implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Password checkLogin(String id, String pass) {
		String sql = "select * from password where id=? and password=? and type=1";
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
	public int updateTeam(String team_name, String project, String team_leader, String leader_phone, String leader_email, String team_id){
		String sql="update team set team_name = ?, project = ?, team_leader = ?, leader_phone = ?, leader_email =? where team_id = ?";
		return jdbcTemplate.update(sql,team_name,project, team_leader, leader_phone, leader_email, team_id);
	}
	
	@Override
	public int updatePassword(String id, String password){
		String sql="update password set password=? where id=?";
		return jdbcTemplate.update(sql, password, id);
	}
	@Override
	public List<Report> agendaReport(String report_id){
		String sql="select * from upload where report_id=?";
		RowMapper<Report> rowMapper=new BeanPropertyRowMapper<>(Report.class);
		List<Report> reports = jdbcTemplate.query(sql, new Object[]{report_id}, rowMapper);
		return reports;
	}
	@Override
	public int insertReport(Report report){
		String sql="insert upload values(?,?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, report.getReport_id(), report.getUpload_date(), report.getProgress(), report.getHarvest(), report.getNext_aim());
	}
}