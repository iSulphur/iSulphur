package com.sulphur.teacher;

public class Review {
	private String review_id;
	private String report_id;
	private String ranking;
	private String suggest;
	private String upload_status;

	public Review(){}
	public Review(String review_id, String report_id, String ranking, String suggest, String upload_status){
		this.review_id = review_id;
		this.report_id = report_id;
		this.ranking = ranking;
		this.suggest = suggest;
		this.upload_status= upload_status;

	}
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
	public String getranking() {
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
	public String getupload_status() {
		return upload_status;
	}
	public void setupload_status(String upload_status) {
		this.upload_status = upload_status;
	}
}
