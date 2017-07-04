package com.sulphur.admin;

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

import net.sf.json.JSON;
import net.sf.json.JSONArray;

@Controller
@RequestMapping("/admin")
public class AdminAction {

	@Autowired
	private AdminDao adminDao;

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public @ResponseBody Message login(@RequestParam("id") String id,
			@RequestParam("password") String password, HttpServletRequest req) {
		Password res = adminDao.checkLogin(id, password);
		Message msg;
		if (res != null) {
			HttpSession session = req.getSession();
			session.setMaxInactiveInterval(1200);
			session.setAttribute("admin_id", id);
			msg = new Message("1");
		}
		else{
			msg = new Message("0");
		}
		return msg;
	}

	@RequestMapping(value = "/update_pwd.do", method = RequestMethod.POST)
	public @ResponseBody Message updatePass(@RequestParam("password") String password, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		String user = new String();
		Message msg;
		if(session != null){
			user = (String) session.getAttribute("admin_id");
			Integer res = adminDao.updatePass(user, password);
			msg = new Message(res.toString());
		}
		else{
			msg = new Message(Message.ERROR, "ERROR", "Not Login!");
		}
		return msg;
	}
	
	@RequestMapping(value="/update_info.do", method = RequestMethod.POST)
	public @ResponseBody Message updateInfo(HttpServletRequest req){
		HttpSession session = req.getSession(false);
		String user = new String();
		Message msg;
		if(session != null){
			user = (String) session.getAttribute("admin_id");
//			user = "sulphur";
			String adminName = req.getParameter("admin_name");
			String adminPhone = req.getParameter("admin_phone");
			Admin admin = new Admin(user, adminName, adminPhone);
			Integer res = adminDao.updateInfo(admin);
			msg = new Message(res.toString());
		}
		else{
			msg = new Message(Message.ERROR, "ERROR", "Not Login!");
		}
		return msg;
	}
	
	@RequestMapping(value="/get_info.do", method= RequestMethod.GET)
	public @ResponseBody Message getInfo(HttpServletRequest req){
		HttpSession session = req.getSession(false);
		String user = new String();
		Message msg;
		if(session != null){
			user = (String) session.getAttribute("admin_id");
//			user = "sulphur";
			Admin res = adminDao.getInfoByID(user);
			msg = new Message(res);
		}
		else{
			msg = new Message(Message.ERROR, "ERROR", "Not Login!");
		}
		return msg;
	}
	
//	@RequestMapping(value="/team.do")
//	public @ResponseBody Message teamManage(@RequestParam("action") String action, HttpServletRequest req){
//		HttpSession session = req.getSession(false);
//		String user = new String();
//		Message msg = new Message();
//		if(session != null){
//			user = (String) session.getAttribute("admin_id");
////			user = "sulphur";
//			int res = adminDao.checkPrivileges(user);
//			// Admin
//			if(res == 0){
//				// list all team
//				if(action == "list"){
//					List<Team> r = adminDao.findAllTeam();
//					JSONArray jsonArray = JSONArray.fromObject(r);
//					msg.setStatusCode(200);
//					msg.setMsgType("info");
//					msg.setMsgContent(r);
//					return msg;
//				}
//				else if (action == "add") {
//					Team team = new Team(req.getParameter("team_id"),req.getParameter("team_name"),req.getParameter("team_id"),req.getParameter("team_id"),req.getParameter("team_id"));
//				}
//			}
//			else{
//				msg.setStatusCode(400);
//				msg.setMsgType("warning");
//				msg.setMsgContent("forbidden!");
//				return JSONArray.fromObject(msg).toString();
//			}
//		}
//		else{
//			msg.setStatusCode(400);
//			msg.setMsgType("warning");
//			msg.setMsgContent("please login as admin.");
//			return JSONArray.fromObject(msg).toString();
//		}
//		return "hhh";
//	}
}
