package com.sulphur.admin;

public class Result {
	private String reportID;
	private String finalResult;
	
	public Result(){
		
	}
	
	public Result(String id, String finalRes){
		reportID = id;
		finalResult = finalRes;
	}
	
	public String getReportID() {
		return reportID;
	}
	public void setReportID(String reportID) {
		this.reportID = reportID;
	}
	public String getFinalResult() {
		return finalResult;
	}
	public void setFinalResult(String finalResult) {
		this.finalResult = finalResult;
	}
}
