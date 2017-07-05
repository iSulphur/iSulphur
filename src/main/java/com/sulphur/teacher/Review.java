package com.sulphur.teacher;

public class Review {
	
	String review_id;
	String report_id;
	String teacher_id;
	String ranking;
	String suggest;

	public String getReview_id() {
		return review_id;
	}
	public void setReview_id(String review_id) {
		this.review_id = review_id;
	}
	public String getReport_id() {
		return report_id;
	}
	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}
	public String getteacher_id() {
		return teacher_id;
	}
	public void setteacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String ranking() {
		return ranking;
	}
	public void setranking(String ranking) {
		this.ranking = ranking;
	}
	public String getsuggest() {
		return suggest;
	}
	public void setsuggest(String suggest) {
		this.suggest = suggest;
	}	
}
