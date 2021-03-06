package com.sulphur.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.sulphur.admin.Password;
import com.sulphur.admin.ReportTask;
import com.sulphur.admin.Result;
import com.sulphur.admin.Team;
import com.sulphur.teacher.Review;


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
		String sql="update team set team_name = ?, project = ?, team_leader = ?, leader_phone = ?, leader_mail =? where team_id = ?";
		return jdbcTemplate.update(sql,team_name,project, team_leader, leader_phone, leader_email, team_id);
	}
	
	@Override
	public int updatePassword(String id, String password){
		String sql="update password set password=? where id=?";
		return jdbcTemplate.update(sql, password, id);
	}
	@Override
	public List<Report> agendaReport(String user_id){
		String sql="select * from report where team_name in (select team_name from team where team_id =?) and upload_status=0";
		RowMapper<Report> rowMapper=new BeanPropertyRowMapper<>(Report.class);
		List<Report> reports = jdbcTemplate.query(sql, new Object[]{user_id}, rowMapper);
		return reports;
	}
	@Override
	public int insertReport(Report report){

		String sql="insert report values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, report.getReport_id(), report.getUpload_date(), report.getTeam_name(), report.getProject(), report.getTeam_leader(), report.getLeader_phone(), report.getLeader_mail(), report.getProgress(), report.getHarvest(), report.getNext_aim(), report.getUpload_status(), "0", report.getReport_task_id());
	}
	
	@Override
	public List<Report> viewReport(String user_id){
		
		String sql="select * from report where team_name in (select team_name from team where team_id =?) and upload_status=1";
		RowMapper<Report> rowMapper=new BeanPropertyRowMapper<>(Report.class);
		List<Report> reports = jdbcTemplate.query(sql, new Object[]{user_id}, rowMapper);
		return reports;
	}
	
	@Override
	public List<ReportTask> getTask(String user_id){
		
		String sql = "select * from report_task";
		RowMapper<ReportTask> rowMapper=new BeanPropertyRowMapper<>(ReportTask.class);
		List<ReportTask> tasks = jdbcTemplate.query(sql, new Object[]{}, rowMapper);
		return tasks;
	}
	
	@Override
	public Team getTeam(String user_id){
		String sql = "select * from team where team_id = ?";
		RowMapper<Team> rowMapper=new BeanPropertyRowMapper<>(Team.class);
		Team info = jdbcTemplate.queryForObject(sql, new Object[]{user_id}, rowMapper);
		return info;
	}
	
	@Override
	public List<Review> getReviewByID(String report_id){
		String sql = "select * from review where report_id = ?";
		RowMapper<Review> rowMapper=new BeanPropertyRowMapper<>(Review.class);
		List<Review> info = jdbcTemplate.query(sql, new Object[]{report_id}, rowMapper);
		return info;
	}

	@Override
	public Result findResult(String id) {
		String sql = "select * from result where report_id=?";
		RowMapper<Result> rowMapper=new BeanPropertyRowMapper<>(Result.class);
		List<Result> res = jdbcTemplate.query(sql, new Object[]{id}, rowMapper);
		if(res != null){
			return res.get(0);			
		}
		return null;
	}
}
