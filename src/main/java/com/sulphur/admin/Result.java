package com.sulphur.admin;

public class Result {
	private String reportId;
	private String finalResult;
	
	public Result(){
		
	}
	
	public Result(String id, String finalRes){
		reportId = id;
		finalResult = finalRes;
	}
	
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getFinalResult() {
		return finalResult;
	}
	public void setFinalResult(String finalResult) {
		this.finalResult = finalResult;
	}
}
