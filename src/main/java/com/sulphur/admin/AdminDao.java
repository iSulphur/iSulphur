package com.sulphur.admin;

import java.util.List;
import java.util.Map;
import com.sulphur.user.Report;
import com.sulphur.teacher.Review;

public interface AdminDao {
	// Login
	public Password checkLogin(String name, String pass);
	public int checkPrivileges(String user);
	
	// Basic info
	public int updatePass(String name, String pass);
	public int updateInfo(Admin admin);
	public Admin getInfoByID(String name);
	
	// Team Management
	public List<Team> findAllTeam();
	public int addTeam(Team team);
	public int delTeam(String teamID);
	public int updateTeam(Team team);
	public Map<String, Object> findTeamById(String teamID);
	
	// Report task management
	public List<ReportTask> findAllCurrentTask();
	public List<ReportTask> findAllHistoryTask();
	public int setTaskStatus(String id, int status);
	public int addNewTask(ReportTask t);
	
	// Report management
	public List<Report> findAllReport();
	public Review findReview(String reportID);
	public int addResult(Result r);
}
