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
	public List<Report> showReport(String report_task_id);
	
	//choose
	public Report choose(String report_id);
	//cao gao xiang
	public List<Review> disuploadReview();
	//yi shang chuan
	public List<Review> uploadReview();
	//baocun
	public int updatereview(String ranking,String suggest,String Review_id);
	//shang chuan
	public int uploadreview(String ranking,String suggest,String Review_id);
}
