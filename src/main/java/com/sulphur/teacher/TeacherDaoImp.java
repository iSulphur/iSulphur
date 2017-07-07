package com.sulphur.teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	public int review(String report_id,String ranking,String suggest)
	{
		String sql1="insert into review(?,?,?,?,0)";
		int a= jdbcTemplate.update(sql1,report_id,ranking,suggest);
		if(a==1){
			String sql2="update upload set review_type=review_type+1";
			int b=jdbcTemplate.update(sql2,report_id,ranking,suggest);
			if(b==0)return 0;
			else return 1;
		}
		else return -1;
	}
	
	@Override
	public int review1(String report_id,String ranking,String suggest)
	{
		String sql1="insert into review(?,?,?,?,1)";
		int a= jdbcTemplate.update(sql1,report_id,ranking,suggest);
		if(a==1){
			String sql2="update upload set review_type=review_type+1";
			int b=jdbcTemplate.update(sql2,report_id,ranking,suggest);
			if(b==0)return 0;
			else return 1;
		}
		else return -1;
	}
	
	//查看所有报告
	@Override
	public List<Report> showReport() {
		String sql = "select * from upload";
		List<Report> res = jdbcTemplate.query(sql, new RowMapper<Report>(){
			@Override 
			public Report mapRow(ResultSet rs, int num) throws SQLException{
				Report a = new Report();
				a.setReport_id(rs.getString("report_id"));
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
	public Report choose(String report_id)
	{

		String sql = "select * from upload where report_id=?";
		List<Report> res = jdbcTemplate.query(sql, new Object[] {report_id}, new RowMapper<Report>(){
			@Override
			public Report mapRow(ResultSet rs, int num) throws SQLException{
				Report a = new Report();
				a.setReport_id(rs.getString("report_id"));
				a.setUpload_date(rs.getString("upload_date"));
				a.setTeam_name(rs.getString("team_name"));
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
	
	
	@Override
	public List<Report> disuploadReview()
	{
		String sql="select * from upload where upload_status=1";
		List<Report> res = jdbcTemplate.query(sql, new RowMapper<Report>(){
			@Override 
			public Report mapRow(ResultSet rs, int num) throws SQLException{
				Report a = new Report();
				a.setUpload_status(rs.getString("upload_status"));
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
	
	
	
}
	

