package com.sulphur.admin;

public class Message {
	private int statusCode;
	private String msgHead;
	private Object msgContent;
	
	public static final int SULPHUR = 200;
	public static final int WARNING = 300;
	public static final int ERROR = 400;
	
	public Message(){
		
	}
	
	public Message(Object msgContent){
		this.statusCode = Message.SULPHUR;
		this.msgHead = "INFO";
		this.msgContent = msgContent;
	}
	
	public Message(int status_code, String msgHead, Object msgContent){
		this.statusCode = status_code;
		this.msgHead = msgHead;
		this.msgContent = msgContent;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMsgType() {
		return msgHead;
	}
	public void setMsgType(String msgHead) {
		this.msgHead = msgHead;
	}
	public Object getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(Object msgContent) {
		this.msgContent = msgContent;
	}
}
