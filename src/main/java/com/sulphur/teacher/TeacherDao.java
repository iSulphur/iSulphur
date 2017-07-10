package com.sulphur.teacher;

import java.util.List;
import java.util.Map;

import com.sulphur.admin.Password;
import com.sulphur.user.*;

public interface TeacherDao 
{
	//review
	public int review(String report_id,String ranking,String suggest);
	
	public int review1(String report_id,String ranking,String suggest);
	
	//see what he should review
	public List<Report> showReport();
	
	//choose
	public Report choose(String report_id);
	
	public List<Review> disuploadReview();
	
	public List<Review> uploadReview();
	
	public int updatereview(String ranking,String suggest,String Review_id);
	
	public int uploadreview(String ranking,String suggest,String Review_id);
}
