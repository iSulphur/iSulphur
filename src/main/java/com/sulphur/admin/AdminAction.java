package com.sulphur.admin;

import java.util.List;
import java.util.Map;

//import javax.json.JsonArray;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.sulphur.encoding.Base64;
import com.sulphur.teacher.Review;
import com.sulphur.user.Report;

@Controller
@RequestMapping("/admin")
public class AdminAction {

	@Autowired
	private AdminDao adminDao;

	// Administrator action.
	@RequestMapping(value = "/admin.do")
	public @ResponseBody Message adminAction(HttpServletRequest req) {
		String action = req.getParameter("action");
		String user = new String();
		Message msg;
		HttpSession session;
		// Login
		if(action == null || action.equals("")){
			msg = new Message(Message.WARNING, "No Action", "Please provide what action to do.");
			return msg;
		}
		else if (action.equals("login")){
			String id = req.getParameter("id");
			String password = req.getParameter("password");
			
			Password res = adminDao.checkLogin(id, password);
			if (res != null) {
				session = req.getSession();
				session.setMaxInactiveInterval(1200);
				session.setAttribute("admin_id", id);
				msg = new Message("1");
			}
			else{
				msg = new Message("0");
			}	
			return msg;
		}
		else{
			session = req.getSession(false);	
		}
			
		if(session != null){
			user = (String) session.getAttribute("admin_id");
//			user = "sulphur";
			int res = adminDao.checkPrivileges(user);
			if(res == 0){
				// update password
				if(action.equals("update_pwd")){
					String password = req.getParameter("password");
					Integer r = adminDao.updatePass(user, password);
					msg = new Message(r);
				}
				// update information
				else if (action.equals("update_info")) {
					String adminName = req.getParameter("admin_name");
					String adminPhone = req.getParameter("admin_phone");
					Admin admin = new Admin(user, adminName, adminPhone);
					Integer r = adminDao.updateInfo(admin);
					msg = new Message(r);
				}
				// get information
				else if (action.equals("get_info")) {
					Admin r = adminDao.getInfoByID(user);
					msg = new Message(r);
				}
				else{
					msg = new Message(Message.WARNING, "Unkown Action.", action);
				}
				//
			}
			else{
				msg = new Message(Message.ERROR, "Forbidden", "Permission denied!");
			}
		}
		else{
			msg = new Message(Message.ERROR, "ERROR", "Not Login!");
		}
		return msg;
	}

	// Team action
	@RequestMapping(value="/team.do")
	public @ResponseBody Message teamManage(HttpServletRequest req){
		String action = req.getParameter("action");
		HttpSession session = req.getSession(false);
		String user = new String();
		Message msg;
		if(session != null){
			user = (String) session.getAttribute("admin_id");
//			user = "sulphur";
			int res = adminDao.checkPrivileges(user);
			if(res == 0){
				// list all team
				if(action == null || action.equals("")){
					msg = new Message(Message.WARNING, "No Action", "Please provide what action to do.");
				}
				else if(action.equals("findall")){
					List<Team> r = adminDao.findAllTeam();
					// form response message
					if(r != null){
						msg = new Message(r);
					}
					else{
						msg = new Message(Message.WARNING, "No Team", "No Team Found!");
					}
				}
				// add team
				else if (action.equals("add")) {
					String teamID = req.getParameter("team_id");
					String teamName = req.getParameter("team_name");
					String project = req.getParameter("project");
					String teamLeader = req.getParameter("team_leader");
					String leaderPhone = req.getParameter("leader_phone");
					String leaderMail = req.getParameter("leader_mail");
					// some check
					String[] p = {teamID,teamName,project,teamLeader,leaderPhone,leaderMail};
					if(adminDao.paraCheck(p)){
						Team team = new Team(teamID,teamName,project,teamLeader,leaderPhone,leaderMail);
						int r = adminDao.addTeam(team);
						msg = new Message(r);
					}else{
						msg = new Message(Message.ERROR, "ERROR", "Parameter Error!");
					}
						
				}
				else if (action.equals("del")) {
					String teamID = req.getParameter("team_id");
					// some check
					int r = adminDao.delTeam(teamID);
					msg = new Message(r);
				}
				else if (action.equals("update_info")){
					String teamID = req.getParameter("team_id");
					String teamName = req.getParameter("team_name");
					String project = req.getParameter("project");
					String teamLeader = req.getParameter("team_leader");
					String leaderPhone = req.getParameter("leader_phone");
					String leaderMail = req.getParameter("leader_mail");
					// some check
					String[] p = {teamID,teamName,project,teamLeader,leaderPhone,leaderMail};
					if(adminDao.paraCheck(p)){
						Team team = new Team(teamID,teamName,project,teamLeader,leaderPhone,leaderMail);
						int r = adminDao.updateTeam(team);
						msg = new Message(r);
					}else{
						msg = new Message(Message.ERROR, "ERROR", "Parameter Error!");
					}
				}
				else if (action.equals("find")){
					String teamID = req.getParameter("team_id");
					String[] p = {teamID};
					if(adminDao.paraCheck(p)){
						// some check
						Map<String, Object> r = adminDao.findTeamById(teamID);
						msg = new Message(r);
					}else{
						msg = new Message(Message.ERROR, "ERROR", "Parameter Error!");
					}
				}
				else{
					msg = new Message(Message.WARNING, "Unkown Action.", action);
				}
				//
			}
			else{
				msg = new Message(Message.ERROR, "Forbidden", "Permission denied!");
			}
		}
		else{
			msg = new Message(Message.ERROR, "ERROR", "Not Login!");
		}
		return msg;
	}

	// Task action
	@RequestMapping(value="/task.do")
	public @ResponseBody Message taskAction(HttpServletRequest req){
		String action = req.getParameter("action");
		HttpSession session = req.getSession(false);
		String user = new String();
		Message msg;
		if(session != null){
			user = (String) session.getAttribute("admin_id");
//			user = "sulphur";
			int res = adminDao.checkPrivileges(user);
			if(res == 0){
				// list all current tasks
				if(action == null || action.equals("")){
					msg = new Message(Message.WARNING, "No Action", "Please provide what action to do.");
				}
				else if(action.equals("findcur")){
					List<ReportTask> r = adminDao.findAllCurrentTask();
					// form response message
					if(r != null){
						msg = new Message(r);
					}
					else{
						msg = new Message(Message.WARNING, "No Current Task", "No Task Found!");
					}
				}
				// list all history tasks
				else if (action.equals("findhis")) {
					List<ReportTask> r = adminDao.findAllHistoryTask();
					if(r != null){
						msg = new Message(r);
					}
					else{
						msg = new Message(Message.WARNING, "No History Task", "No Task Found!");
					}
				}
				// set status
				else if (action.equals("set_status")) {
					String id = req.getParameter("report_task_id");
					String status = req.getParameter("task_status");
					
					String[] p = {id, status};
					if(adminDao.paraCheck(p)){
						int r = adminDao.setTaskStatus(id, Integer.parseInt(status));
						msg = new Message(r);
					}else{
						msg = new Message(Message.ERROR, "ERROR", "Parameter Error!");
					}
				}
				// add new
				else if (action.equals("add")){
					String rTI = req.getParameter("report_task_id");
					String tP = req.getParameter("task_property");
					String bT = req.getParameter("begin_time");
					String eT = req.getParameter("end_time");
					String mST = req.getParameter("max_submit_time");
					String tR = req.getParameter("task_remake");
					String status = req.getParameter("task_status");
					String[] p = {rTI, tP, bT, eT, mST, tR, status};
					// some check
					if(adminDao.paraCheck(p)){
						ReportTask t = new ReportTask(rTI,tP,bT,eT,Integer.parseInt(mST),tR,Integer.parseInt(status));
						int r = adminDao.addNewTask(t);
						msg = new Message(r);
					}else{
						msg = new Message(Message.ERROR, "ERROR", "Parameter Error!");
					}
				}
				else{
					msg = new Message(Message.WARNING, "Unkown Action.", action);
				}
				//
			}
			else{
				msg = new Message(Message.ERROR, "Forbidden", "Permission denied!");
			}
		}
		else{
			msg = new Message(Message.ERROR, "ERROR", "Not Login!");
		}
		return msg;
	}
	
	// Report action
	@RequestMapping(value="/report.do")
	public @ResponseBody Message reportAction(HttpServletRequest req){
		String action = req.getParameter("action");
		HttpSession session = req.getSession(false);
		String user = new String();
		Message msg;
		if(session != null){
			user = (String) session.getAttribute("admin_id");
//			user = "sulphur";
			int res = adminDao.checkPrivileges(user);
			if(res == 0){
				if(action == null || action.equals("")){
					msg = new Message(Message.WARNING, "No Action", "Please provide what action to do.");
				}
				// find all report
				else if(action.equals("findall")){
					List<Report> r = adminDao.findAllReport();
					// form response message
					if(r != null){
						msg = new Message(r);
					}
					else{
						msg = new Message(Message.WARNING, "No Report", "No Report Found!");
					}
				}
				// find review
				else if (action.equals("findrev")) {
					String reportID = req.getParameter("report_id");
					
					String[] p = {reportID};
					// some check
					if(adminDao.paraCheck(p)){
						Review r = adminDao.findReview(reportID);
						if(r != null){
							msg = new Message(r);
						}
						else{
							msg = new Message(Message.WARNING, "No Review", "No Review Found!");
						}
					}else{
						msg = new Message(Message.ERROR, "ERROR", "Parameter Error!");
					}

				}
				// add result
				else if (action.equals("add_result")){
					String rI = req.getParameter("report_id");
					String fR = req.getParameter("final_result");
					
					String[] p = {rI, fR};
					// some check
					if(adminDao.paraCheck(p)){
						Result result = new Result(rI, fR);
						int r = adminDao.addResult(result);
						msg = new Message(r);
					}else{
						msg = new Message(Message.ERROR, "ERROR", "Parameter Error!");
					}

				}
				else if (action.equals("find_result")){
					String rI = req.getParameter("report_id");
					
					String[] p = {rI};
					// some check
					if(adminDao.paraCheck(p)){
						Result result = adminDao.findResult(rI);
						msg = new Message(result);
					}else{
						msg = new Message(Message.ERROR, "ERROR", "Parameter Error!");
					}

				}
				else{
					msg = new Message(Message.WARNING, "Unkown Action.", action);
				}
				//
			}
			else{
				msg = new Message(Message.ERROR, "Forbidden", "Permission denied!");
			}
		}
		else{
			msg = new Message(Message.ERROR, "ERROR", "Not Login!");
		}
		return msg;
	}

	//Get review url
	@RequestMapping(value="/url.do")
	public @ResponseBody Message getReviewUrl(HttpServletRequest req){
		String user = new String();
		Message msg;
		HttpSession session;
			
		String id = req.getParameter("task_id");
			
		session = req.getSession(false);

		if(session != null){
			user = (String) session.getAttribute("admin_id");
//			user = "sulphur";
			int res = adminDao.checkPrivileges(user);
			if(res == 0){
				// update password
				
				String url = "http://"+ req.getServerName()+":"+req.getServerPort()+req.getContextPath() + "/teacher/task.html?t=" +Base64.getBase64(id);
				msg = new Message(url);
			}
			else{
				msg = new Message(Message.ERROR, "Forbidden", "Permission denied!");
			}
		}
		else{
			msg = new Message(Message.ERROR, "ERROR", "Not Login!");
		}
		return msg;
	}
}
