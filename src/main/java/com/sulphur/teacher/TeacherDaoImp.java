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
		String sql1="insert into review values(?,?,?,?,0)";
		int a= jdbcTemplate.update(sql1,report_id+ranking,report_id,ranking,suggest);
		if(a==1){
			String sql2="update report set review_status=review_status+1 where report_id=?";
			int b=jdbcTemplate.update(sql2,report_id);
			if(b==0)return 0;
			else return 1;
		}
		else return -1;
	}
	
	@Override
	public int review1(String report_id,String ranking,String suggest)
	{
		String sql1="insert into review values(?,?,?,?,1)";
		int a= jdbcTemplate.update(sql1,report_id+ranking,report_id,ranking,suggest);
		if(a==1){
			String sql2="update report set review_status=review_status+1 where report_id=?";
			int b=jdbcTemplate.update(sql2,report_id);
			if(b==0)return 0;
			else return 1;
		}
		else return -1;
	}
	
	//查看所有报告
	@Override
	//select has been changed with report_task_id.
	public List<Report> showReport(String report_task_id) {
		String sql = "select * from report where report_task_id=?";
		List<Report> res = jdbcTemplate.query(sql,new Object[]{report_task_id},new RowMapper<Report>(){
			@Override 
			public Report mapRow(ResultSet rs, int num) throws SQLException{
				Report a = new Report();
				a.setReport_id(rs.getString("report_id"));
				a.setUpload_date(rs.getString("upload_date"));
				a.setTeam_name(rs.getString("team_name"));
				a.setProject(rs.getString("project"));
				a.setTeam_leader(rs.getString("team_leader"));
				a.setLeader_phone(rs.getString("leader_phone"));
				a.setLeader_mail(rs.getString("leader_mail"));
				a.setProgress(rs.getString("progress"));
				a.setHarvest(rs.getString("harvest"));
				a.setNext_aim(rs.getString("next_aim"));
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
	@Override
	public Report choose(String report_id)
	{

		String sql = "select * from report where report_id=?";
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
	public List<Review> disuploadReview()
	{
		String sql="select * from review where upload_status=0";
		List<Review> res = jdbcTemplate.query(sql, new RowMapper<Review>(){
			@Override 
			public Review mapRow(ResultSet rs, int num) throws SQLException{
				Review a=new Review();
				a.setReview_id(rs.getString("review_id"));
				a.setReport_id(rs.getString("report_id"));
				a.setranking(rs.getString("ranking"));
				a.setsuggest(rs.getString("suggest"));
				a.setupload_status(rs.getString("upload_status"));
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
	@Override
	public List<Review> uploadReview()
	{
		String sql="select * from review where upload_status=1 order by ranking desc";
		List<Review> res = jdbcTemplate.query(sql, new RowMapper<Review>(){
			@Override 
			public Review mapRow(ResultSet rs, int num) throws SQLException{
				Review a=new Review();
				a.setReview_id(rs.getString("review_id"));
				a.setReport_id(rs.getString("report_id"));
				a.setranking(rs.getString("ranking"));
				a.setsuggest(rs.getString("suggest"));
				a.setupload_status(rs.getString("upload_status"));
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
	
	
	@Override
	public int updatereview(String ranking,String suggest,String Review_id)
	{
		String sql = "update review set ranking=?,suggest=?,upload_status='0' where review_id=?";
		return jdbcTemplate.update(sql,ranking,suggest,Review_id);
	}

	@Override
	public int uploadreview(String ranking,String suggest,String Review_id)
	{
		String sql = "update review set ranking=?,suggest=?,upload_status='1' where review_id=?";
		return jdbcTemplate.update(sql,ranking,suggest,Review_id);
	}

	
}
	

