package com.sulphur.admin;

public class ReportTask {
	private String reportTaskID;
	private String taskProperty;
	private String beginTime;
	private String endTime;
	private int maxSubmitTime;
	private String taskRemake;
	private TaskStatus taskStatus;
	
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
	public TaskStatus getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}
	
}
