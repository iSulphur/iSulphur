package com.sulphur.admin;

public class TaskStatus {
	private int statusCode;
	private Object statusDescription;
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public Object getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(Object statusDescription) {
		this.statusDescription = statusDescription;
	}

	// Task status
	public static final int BEGIN = 100;
	public static final int TS = 200;
	public static final int PS = 300;
	public static final int TR = 400;
	public static final int AR = 500;
	public static final int END = 600;

}
