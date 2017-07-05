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
//			user = (String) session.getAttribute("admin_id");
			user = "sulphur";
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
//			user = (String) session.getAttribute("admin_id");
			user = "sulphur";
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
					Team team = new Team(teamID,teamName,project,teamLeader,leaderPhone,leaderMail);
					int r = adminDao.addTeam(team);
					msg = new Message(r);
				}
				else if (action.equals("del")) {
					String teamID = req.getParameter("team_id");
					// some check
					int r = adminDao.delTeam(teamID);
					msg = new Message(r);
				}
				else if (action.equals("update")){
					String teamID = req.getParameter("team_id");
					String teamName = req.getParameter("team_name");
					String project = req.getParameter("project");
					String teamLeader = req.getParameter("team_leader");
					String leaderPhone = req.getParameter("leader_phone");
					String leaderMail = req.getParameter("leader_mail");
					// some check
					Team team = new Team(teamID,teamName,project,teamLeader,leaderPhone,leaderMail);
					int r = adminDao.updateTeam(team);
					msg = new Message(r);
				}
				else if (action.equals("find")){
					String teamID = req.getParameter("team_id");
					// some check
					Map<String, Object> r = adminDao.findTeamById(teamID);
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

}
