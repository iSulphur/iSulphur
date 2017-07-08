package com.sulphur.teacher;

import java.util.List;
import java.util.Map;

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
import com.sulphur.admin.Team;
import com.sulphur.user.Report;

import net.sf.json.JSON;
import net.sf.json.JSONArray;

import com.sulphur.admin.Message;
import java.util.Date; 
import java.util.Calendar; 
import java.text.SimpleDateFormat; 

@Controller
@RequestMapping("/teacher")
public class TeacherAction {

	@Autowired
	private TeacherDao teacherdao;
	
	@RequestMapping(value = "/choose.do", method = RequestMethod.POST)
	public @ResponseBody Message show_choose(HttpServletRequest req) 
	{
		Message message;
		String repp=req.getParameter("report_id");
		Report a=teacherdao.choose(repp);
		message=new Message(Message.SULPHUR,"OK",a);
		return message;
	}
	
	@RequestMapping(value = "/show.do", method = RequestMethod.POST)
	public @ResponseBody Message show_all()
	{
		List<Report> result = teacherdao.showReport();
		if(result.isEmpty())return new Message(Message.ERROR,"none",null);
		else return new Message(Message.SULPHUR,"success",result);
	}
	
	@RequestMapping(value = "/review.do", method =RequestMethod.POST)
	public @ResponseBody Message reviewreport(@RequestParam("ranking")String ranking,@RequestParam("suggest")String suggest,HttpServletRequest req )
	{
		String csysb=req.getParameter("report_id");
		int a=teacherdao.review(csysb,ranking,suggest);
		Message msg = null;
		if(a==1)msg= new Message(Message.SULPHUR,"success",1);
		else if(a==0)msg= new Message(Message.SULPHUR,"update_error",0);
		else if(a==-1)msg= new Message(Message.SULPHUR,"review_error",-1);
		return msg;
		
	}
	
	@RequestMapping(value = "/review1.do", method =RequestMethod.POST)
	public @ResponseBody Message reviewreport1(@RequestParam("ranking")String ranking,@RequestParam("suggest")String suggest,HttpServletRequest req )
	{
		String csysb=req.getParameter("report_id");
		int a=teacherdao.review1(csysb,ranking,suggest);
		Message msg = null;
		if(a==1)msg= new Message(Message.SULPHUR,"success",1);
		else if(a==0)msg= new Message(Message.SULPHUR,"update_error",0);
		else if(a==-1)msg= new Message(Message.SULPHUR,"review_error",-1);
		return msg;
		
	}
	
	
	@RequestMapping(value="/review_manager.do")
	public @ResponseBody Message reportManage(HttpServletRequest req){
		String action = req.getParameter("action");
		Message msg;
			if(action == null || action.equals("")){
				msg = new Message(Message.WARNING, "No Action", "Please provide what action to do.");
			}
			else if(action.equals("findall0")){
				List<Review> r = teacherdao.disuploadReview();
				// form response message
				if(r != null){
					msg = new Message(r);
				}
				else{
					msg = new Message(Message.WARNING, "No disuploadReport", "No disuploadRoprt Found!");
				}
			}
			else if (action.equals("update")){
				String review_id=req.getParameter("review_id");
				String ranking = req.getParameter("ranking");
				String suggest = req.getParameter("suggest");
				// some check
				int r = teacherdao.updatereview(ranking, suggest, review_id);
				msg = new Message(r);
			}
			else if (action.equals("upload")){
				String review_id=req.getParameter("review_id");
				String ranking = req.getParameter("ranking");
				String suggest = req.getParameter("suggest");
				// some check
				int r = teacherdao.uploadreview(ranking, suggest, review_id);
				msg = new Message(r);
			}
			else if(action.equals("findall1")){
				List<Review> r = teacherdao.uploadReview();
				// form response message
				if(r != null){
					msg = new Message(r);
				}
				else{
					msg = new Message(Message.WARNING, "No disuploadReport", "No disuploadRoprt Found!");
				}
			}
			
			else{
				msg = new Message(Message.WARNING, "Unkown Action.", action);
			}
			return msg;//
			}
	
}
