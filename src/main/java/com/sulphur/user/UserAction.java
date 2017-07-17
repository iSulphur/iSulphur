package com.sulphur.user;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sulphur.admin.Message;
import com.sulphur.admin.Password;
import com.sulphur.admin.ReportTask;
import com.sulphur.admin.Team;
import com.sulphur.teacher.Review;

@Controller
@RequestMapping("/user")
public class UserAction {

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/user.do")
	public @ResponseBody Message userAction(HttpServletRequest req) throws FileNotFoundException, IOException, NoSuchAlgorithmException {
		//get action
		String action = req.getParameter("action");
		
		//if action is null
		if(action == null ||action.equals("")){
			Message msg = new Message(Message.WARNING, "No Action", "Please provide what action to do.");
			return msg;
		}
		
		//login
		else if(action.equals("login")){
			//get parameter
			String id = req.getParameter("id");
			String password = req.getParameter("password");
			
			//convert to md5
			Md5Util test = new Md5Util();
			String md5Password = test.strToMd5(password);
			
			//connect database
			Password res = userDao.checkLogin(id, md5Password);
			Message message;
			
			if (res != null) {//login success
				HttpSession session = req.getSession();
				session.setMaxInactiveInterval(1200);
				session.setAttribute("user_id", id);
				session.setAttribute("user_password", password);
				message = new Message("1");
			} else {//login failure
				message = new Message("0");
			}
			
			return message;
		}
		
		//update_team
		else if(action.equals("update_team")){
			//get parameter
			String team_name = req.getParameter("team_name");
			String project = req.getParameter("project");
			String team_leader = req.getParameter("team_leader");
			String leader_phone = req.getParameter("leader_phone");
			String leader_email = req.getParameter("leader_email");
			HttpSession session = req.getSession(false);
			
			Message message;
			//login status check
			if (session != null) {
				String user = (String) session.getAttribute("user_id");
				Integer res = userDao.updateTeam(team_name, project, team_leader, leader_phone, leader_email, user);
				message = new Message(res.toString());
			} else {
				message = new Message(Message.ERROR, "ERROR", "Not Login!");
			}
			return message;
		}
		
		//update_password
		else if(action.equals("update_password")){
			HttpSession session = req.getSession(false);
			String user = new String();
			Message msg;
			// login status check
			if (session != null) {

				// input password is the same as current password and new password
				// is the same as confirm password
				if (req.getParameter("current_password").equals(session.getAttribute("user_password"))
						&& req.getParameter("new_password").equals(req.getParameter("confirm_password"))) {
					user = (String) session.getAttribute("user_id");
					
					//convert to md5
					Md5Util test = new Md5Util();
					String md5Password = test.strToMd5(req.getParameter("new_password"));
					
					Integer result = userDao.updatePassword(user, md5Password);
					msg = new Message(result.toString());

					// input error
				} else {
					msg = new Message(Message.ERROR, "ERROR", "Input error!");
				}
			} else {
				msg = new Message(Message.ERROR, "ERROR", "Not login!");
			}

			return msg;
		}
		
		//agenda_report
		else if(action.equals("agenda_report")){
			HttpSession session = req.getSession(false);
			Message msg;
			// login status check
			if (session != null) {
				List<Report> result = userDao.agendaReport((String) req.getSession().getAttribute("user_id"));
				msg = new Message(result);
			} else {
				msg = new Message(Message.ERROR, "ERROR", "Not login!");
			}
			return msg;
		}
		
		//edit
		else if(action.equals("edit")){
			//login status check
			Message msg;
			if (req.getSession() != null) {
				Report report = new Report();
				
				//format for report_id and upload_date
				Date date = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
				String upload_date = simpleDateFormat.format(date);
				String report_id = (String)req.getSession().getAttribute("user_id")+upload_date;
				
				//build report
				report.setReport_id(report_id);
				report.setUpload_date(upload_date);
				report.setProject(req.getParameter("project"));
				report.setTeam_name(req.getParameter("team_name"));
				report.setTeam_leader(req.getParameter("team_leader"));
				report.setLeader_phone(req.getParameter("leader_phone"));
				report.setLeader_mail(req.getParameter("leader_mail"));
				report.setProgress(req.getParameter("progress"));
				report.setHarvest(req.getParameter("harvest"));
				report.setNext_aim(req.getParameter("next_aim"));
				report.setReport_task_id(req.getParameter("report_task_id"));
				
				Integer result = userDao.insertReport(report);
				msg = new Message(result.toString());
				
			} else {
				msg = new Message(Message.ERROR, "ERROR", "Not login!");
			}
			return msg;
		}
		
		//review_report
		else if(action.equals("review_report")){
			HttpSession session = req.getSession(false);
			Message msg;
			// login status check
			if (session != null) {
				List<Report> result = userDao.viewReport((String) req.getSession().getAttribute("user_id"));
				msg = new Message(result);
			} else {
				msg = new Message(Message.ERROR, "ERROR", "Not login!");
			}
			return msg;
		}
		
		//upload docx
		else if(action.equals("upload")){
			//login status check
			Message msg;
			if (req.getSession() != null) {
				Report report = new Report();
				
				//format for report_id and upload_date
				Date date = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
				String upload_date = simpleDateFormat.format(date);
				String report_id = (String)req.getSession().getAttribute("user_id")+upload_date;
				
				//build report
				ExportDocx test = new ExportDocx(req.getParameter("path"));
				List<String> table = test.getResult();
				
				report.setReport_id(report_id);
				report.setUpload_date(upload_date);
				report.setProject(table.get(0));
				report.setTeam_name(table.get(1));
				report.setTeam_leader(table.get(2));
				report.setLeader_phone(table.get(3));
				report.setLeader_mail(table.get(4));
				report.setProgress(table.get(5));
				report.setHarvest(table.get(6));
				report.setNext_aim(table.get(7));
				report.setReport_task_id(table.get(8));
				
				Integer result = userDao.insertReport(report);
				msg = new Message(result.toString());
				
			} else {
				msg = new Message(Message.ERROR, "ERROR", "Not login!");
			}
			return msg;
		}
		
		//getTeam
		else if(action.equals("getTeam")){
			HttpSession session = req.getSession(false);
			Message msg;
			// login status check
			if (session != null) {
				Team result = userDao.getTeam((String) req.getSession().getAttribute("user_id"));
				msg = new Message(result);
			} else {
				msg = new Message(Message.ERROR, "ERROR", "Not login!");
			}
			return msg;
		}
		
		//getTask
		else if(action.equals("getTask")){
			HttpSession session = req.getSession(false);
			Message msg;
			// login status check
			if (session != null) {
				List<ReportTask> result = userDao.getTask((String) req.getSession().getAttribute("user_id"));
				msg = new Message(result);
			} else {
				msg = new Message(Message.ERROR, "ERROR", "Not login!");
			}
			return msg;
		}
		
		//getReviewByID
		else if(action.equals("get_review")){
			//parameter
			String report_id = req.getParameter("report_id");
			HttpSession session = req.getSession(false);
			Message msg;
			// login status check
			if (session != null) {
				List<Review> result = userDao.getReviewByID(report_id);
				msg = new Message(result);
			} else {
				msg = new Message(Message.ERROR, "ERROR", "Not login!");
			}
			return msg;
		}
		return new Message(Message.ERROR, "ERROR", "Unknow error");
	}
}