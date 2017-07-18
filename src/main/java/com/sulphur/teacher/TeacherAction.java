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
	
	@RequestMapping(value="/report_manager.do")
	public @ResponseBody Message reportManage(HttpServletRequest req)
	{
		String action=req.getParameter("action");
		Message msg = null;
		if(action == null || action.equals("")){
			msg = new Message(Message.WARNING, "No Action", "Please provide what action to do.");
		}
		else if(action.equals("show"))
		{
			String report_task_id=req.getParameter("report_task_id");
			List<Report> result = teacherdao.showReport(report_task_id);
			if(result.isEmpty())msg=new Message(Message.ERROR,"none",null);
			else msg= new Message(Message.SULPHUR,"success",result);
		}
		else if(action.equals("choose"))
		{
			String repp=req.getParameter("report_id");
			Report a=teacherdao.choose(repp);
			msg=new Message(Message.SULPHUR,"OK",a);
		}
		else if(action.equals("review"))
		{
			String report_id=req.getParameter("report_id");
			String ranking=req.getParameter("ranking");
			String suggest=req.getParameter("suggest");
			int a=teacherdao.review(report_id,ranking,suggest);
			if(a==1)msg= new Message(Message.SULPHUR,"success",1);
			else if(a==0)msg= new Message(Message.SULPHUR,"update_error",0);
			else if(a==-1)msg= new Message(Message.SULPHUR,"review_error",-1);			
		}
		else if(action.equals("review1"))
		{
			String csysb=req.getParameter("report_id");
			String ranking=req.getParameter("ranking");
			String suggest=req.getParameter("suggest");
			int a=teacherdao.review1(csysb,ranking,suggest);
			if(a==1)msg= new Message(Message.SULPHUR,"success",1);
			else if(a==0)msg= new Message(Message.SULPHUR,"update_error",0);
			else if(a==-1)msg= new Message(Message.SULPHUR,"review_error",-1);
		}
		else{
			msg = new Message(Message.WARNING, "Unkown Action.", action);
		}
		return msg;
	
	}
	
	@RequestMapping(value="/review_manager.do")
	public @ResponseBody Message reviewManage(HttpServletRequest req){
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
