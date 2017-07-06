package com.sulphur.teacher;

import java.util.List;

import com.sulphur.admin.Password;
import com.sulphur.user.*;

public interface TeacherDao 
{
	//review
	public int review(String review_id,String Report_id,String ranking,String suggest);
		
	//see what he should review
	public List<Report> showReport();
	
	//choose
	public Report choose(String Report_id);
}
