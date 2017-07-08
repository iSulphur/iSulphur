package com.sulphur.admin;

public class ReportTask {
	private String reportTaskID;
	private String taskProperty;
	private String beginTime;
	private String endTime;
	private int maxSubmitTime;
	private String taskRemake;
	private int taskStatus;
	
	public ReportTask(String rTI, String tP, String bT, String eT, int mST, String tR, int tS){
		reportTaskID = rTI;
		taskProperty = tP;
		beginTime = bT;
		endTime = eT;
		maxSubmitTime = mST;
		taskRemake = tR;
		taskStatus = tS;
	}
	
	public ReportTask(){
		
	}
	
	public String getReportTaskID() {
		return reportTaskID;
	}
	public void setReportTaskID(String reportTaskID) {
		this.reportTaskID = reportTaskID;
	}
	public String getTaskProperty() {
		return taskProperty;
	}
	public void setTaskProperty(String taskProperty) {
		this.taskProperty = taskProperty;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getMaxSubmitTime() {
		return maxSubmitTime;
	}
	public void setMaxSubmitTime(int maxSubmitTime) {
		this.maxSubmitTime = maxSubmitTime;
	}
	public String getTaskRemake() {
		return taskRemake;
	}
	public void setTaskRemake(String taskRemake) {
		this.taskRemake = taskRemake;
	}
	public int getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}
	
}
