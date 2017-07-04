package com.sulphur.admin;

public class Team {
	private String teamID;
	private String teamName;
	private String project;
	private String teamLeader;
	private String leaderPhone;
	private String leaderMail;
	
	public Team(){
		
	}
	public Team(String teamID, String teamName, String project, String teamLeader, String leaderPhone, String leaderMail){
		this.teamID = teamID;
		this.teamName = teamName;
		this.project = project;
		this.teamLeader = teamLeader;
		this.leaderPhone = leaderPhone;
		this.leaderMail = leaderMail;

	}
	public String getTeamLeader() {
		return teamLeader;
	}
	public void setTeamLeader(String teamLeader) {
		this.teamLeader = teamLeader;
	}
	public String getLeaderPhone() {
		return leaderPhone;
	}
	public void setLeaderPhone(String leaderPhone) {
		this.leaderPhone = leaderPhone;
	}
	public String getLeaderMail() {
		return leaderMail;
	}
	public void setLeaderMail(String leaderMail) {
		this.leaderMail = leaderMail;
	}
	public String getTeamID() {
		return teamID;
	}
	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	
	
}
