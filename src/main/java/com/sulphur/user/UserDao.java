package com.sulphur.user;

import java.util.List;

import com.sulphur.admin.Password;

public interface UserDao {
	public Password checkLogin(String id, String pass);

	public int updateTeam(String team_name, String project, String team_leader, String leader_phone, String leader_email, String team_id);
	
	public int updatePassword(String id, String password);
	
	public List<Report> agendaReport(String user_id);
	
	public int insertReport(Report report);
	
	public List<Report> viewReport(String user_id);
}
