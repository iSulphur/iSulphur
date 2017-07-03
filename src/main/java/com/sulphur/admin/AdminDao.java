package com.sulphur.admin;

import java.util.List;

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
	public Team findTeamById(String teamID);
	
	//Teacher Management
	public List<Teacher> findAllTeacher();
	public int addTeacher(Teacher teacher);
	public int delTeacher(String teacherID);
	public int updateTeacher(Teacher teacher);
	public Teacher findTeacherById(String teacherID);
}
