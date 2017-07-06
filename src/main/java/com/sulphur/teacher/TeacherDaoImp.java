package com.sulphur.teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.sulphur.user.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.sulphur.admin.Admin;
import com.sulphur.admin.Password;
import com.sulphur.admin.Team;

@Service
public class TeacherDaoImp implements TeacherDao 
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	//评审
	@Override
	public int review(String Review_id,String Report_id,String ranking,String suggest)
	{
		String sql="insert into review(?,?,?,?)";
		return jdbcTemplate.update(sql,Review_id,Report_id,ranking,suggest);
	}
	//查看所有报告
	@Override
	public List<Report> showReport() {
		String sql = "select * from upload";
		List<Report> res = jdbcTemplate.query(sql, new RowMapper<Report>(){
			@Override
			public Report mapRow(ResultSet rs, int num) throws SQLException{
				Report a = new Report();
				a.setReport_id(rs.getString("Report_id"));
				return a;
			}
		});
		if(res != null){			
			return res;
		}
		else{
			return null;
		}
			
	}
	//选择报告
	@Override
	public Report choose(String Report_id)
	{

		String sql = "select * from upload where Report_id=?";
		List<Report> res = jdbcTemplate.query(sql, new Object[] {Report_id}, new RowMapper<Report>(){
			@Override
			public Report mapRow(ResultSet rs, int num) throws SQLException{
				Report a = new Report();
				a.setReport_id(rs.getString("Report_id"));
				a.setUpload_date(rs.getString("Upload_date"));
				a.setTeam_id(rs.getString("Team_id"));
				a.setProgress(rs.getString("progress"));
				a.setHarvest(rs.getString("harvest"));
				a.setNext_aim(rs.getString("next_aim"));
				return a;
			}
		});
		Report r = null;
		if (res != null && res.size() > 0){
			r = res.get(0);
		}
		return r;
	
	}
	
}
	

