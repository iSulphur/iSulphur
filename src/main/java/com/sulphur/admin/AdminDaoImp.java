package com.sulphur.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.sulphur.teacher.Review;
import com.sulphur.user.Report;

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
	@Override
	public List<Team> findAllTeam() {
		String sql = "select * from team";
		List<Team> res = jdbcTemplate.query(sql, new RowMapper<Team>(){
			@Override
			public Team mapRow(ResultSet rs, int num) throws SQLException{
				Team a = new Team();
				a.setTeamID(rs.getString("team_id"));
				a.setTeamName(rs.getString("team_name"));
				a.setTeamLeader(rs.getString("team_leader"));
				a.setProject(rs.getString("project"));
				a.setLeaderPhone(rs.getString("leader_phone"));
				a.setLeaderMail(rs.getString("leader_mail"));
				return a;
			}
		});
		return res;
	}
	@Override
	public int addTeam(Team team) {
		String sql = "insert into team values(?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,new Object[]{team.getTeamID(),team.getTeamName(),team.getProject(),team.getTeamLeader(),team.getLeaderPhone(),team.getLeaderMail()});
	}
	@Override
	public int delTeam(String teamID) {
		String sql = "delete from team where team_id=?";
		return jdbcTemplate.update(sql,new Object[]{teamID});
	}
	@Override
	public int updateTeam(Team team) {
		String sql = "update team set team_name=?,project=?,team_leader=?,leader_phone=?,leader_mail=? where team_id=?";
		return jdbcTemplate.update(sql,new Object[]{team.getTeamName(),team.getProject(),team.getTeamLeader(),team.getLeaderPhone(),team.getLeaderMail(),team.getTeamID()});
	}
	@Override
	public Map<String, Object> findTeamById(String teamID) {
		String sql = "select * from team where team_id=?";
		Map<String,Object> res = jdbcTemplate.queryForMap(sql, teamID);
		return res;
	}
	@Override
	public int checkPrivileges(String user) {
		String sql = "select type from password where id=?";
		int res = jdbcTemplate.queryForObject(sql,Integer.class,new Object[]{user});
		return res;
	}
	@Override
	public List<ReportTask> findAllCurrentTask() {
		String sql = "select * from report_task where task_status <> 600";
		List<ReportTask> res = jdbcTemplate.query(sql, new RowMapper<ReportTask>(){
			@Override
			public ReportTask mapRow(ResultSet rs, int num) throws SQLException{
				ReportTask a = new ReportTask();
				a.setReportTaskID(rs.getString("report_task_id"));
				a.setTaskProperty(rs.getString("task_property"));
				a.setBeginTime(rs.getString("begin_time"));
				a.setEndTime(rs.getString("end_time"));
				a.setMaxSubmitTime(rs.getInt("max_submit_time"));
				a.setTaskRemake(rs.getString("task_remake"));
				a.setTaskStatus(rs.getInt("task_status"));
				return a;
			}
		});
		return res;
	}
	@Override
	public List<ReportTask> findAllHistoryTask() {
		String sql = "select * from report_task where task_status = 600";
		List<ReportTask> res = jdbcTemplate.query(sql, new RowMapper<ReportTask>(){
			@Override
			public ReportTask mapRow(ResultSet rs, int num) throws SQLException{
				ReportTask a = new ReportTask();
				a.setReportTaskID(rs.getString("report_task_id"));
				a.setTaskProperty(rs.getString("task_property"));
				a.setBeginTime(rs.getString("begin_time"));
				a.setEndTime(rs.getString("end_time"));
				a.setMaxSubmitTime(rs.getInt("max_submit_time"));
				a.setTaskRemake(rs.getString("task_remake"));
				a.setTaskStatus(rs.getInt("task_status"));
				return a;
			}
		});
		return res;
	}
	@Override
	public int setTaskStatus(String id, int status) {
		String sql="update report_task set task_status=? where report_task_id=?";
		return jdbcTemplate.update(sql,new Object[]{id, status});
	}
	@Override
	public int addNewTask(ReportTask t) {
		String sql = "insert into team values(?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,new Object[]{t.getReportTaskID(),t.getTaskProperty(),t.getBeginTime(),t.getEndTime(),t.getMaxSubmitTime(),t.getTaskRemake(),t.getTaskStatus()});
	}
	@Override
	public List<Report> findAllReport() {
		String sql="select * from report";
		RowMapper<Report> rowMapper=new BeanPropertyRowMapper<>(Report.class);
		List<Report> reports = jdbcTemplate.query(sql, rowMapper);
		return reports;
	}
	@Override
	public Review findReview(String reportID) {
		String sql = "select * from review where report_id=?";
		RowMapper<Review> rowMapper=new BeanPropertyRowMapper<>(Review.class);
		List<Review> reviews = jdbcTemplate.query(sql, new Object[]{reportID}, rowMapper);
		if(reviews != null){
			return reviews.get(0);			
		}
		return null;
	}
	@Override
	public int addResult(Result r) {
		String sql = "insert into result values(?,?,?)";
		return jdbcTemplate.update(sql, new Object[]{r.getReportID(),r.getFinalResult()});
	}
	@Override
	public boolean paraCheck(String[] params) {
		for(String p : params){
			if(p == null){
				return false;
			}
			else if (p.equals("")) {
				return false;
			}
		}
		return true;
	}
}
