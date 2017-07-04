package com.sulphur.admin;

public class Message {
	private int statusCode;
	private String msgType;
	private Object msgContent;
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public Object getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(Object msgContent) {
		this.msgContent = msgContent;
	}
}
