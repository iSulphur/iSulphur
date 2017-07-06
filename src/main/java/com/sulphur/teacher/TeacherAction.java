package com.sulphur.teacher;

import java.util.List;

//import javax.json.JsonArray;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sulphur.admin.Password;
import com.sulphur.user.Report;

import net.sf.json.JSON;
import net.sf.json.JSONArray;

import java.util.Date; 
import java.util.Calendar; 
import java.text.SimpleDateFormat; 

@Controller
@RequestMapping("/teacher")
public class TeacherAction {

	@Autowired
	private TeacherDao teacherdao;
	
	@RequestMapping(value = "/choose.do", method = RequestMethod.POST)
	public @ResponseBody Report show_choose(HttpServletRequest req) 
	{
		HttpSession session = req.getSession(false);
		String repp=session.getAttribute("Report_id").toString();
		Report a=teacherdao.choose(repp);
		return a;
	}
	
	@RequestMapping(value = "/show.do", method = RequestMethod.POST)
	public @ResponseBody List<Report> show_all()
	{
		List<Report> result = teacherdao.showReport();
		if(result.isEmpty())return null;
		else return result;
	}
	
	@RequestMapping(value = "/review.do", method =RequestMethod.POST)
	public @ResponseBody String reviewreport(@RequestParam("ranking")String ranking,@RequestParam("suggest")String suggest,HttpServletRequest req )
	{
		HttpSession session = req.getSession(false);
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss");//可以方便地修改日期格式
		String hehe = dateFormat.format( now ); //获取当前时间
		String csysb=session.getAttribute("report_id").toString();
		String dhssb=csysb+hehe;
		int a=teacherdao.review(csysb,dhssb,ranking,suggest);
		if(a==1)return "success";
		else return "fail";
	}
	
	
}
