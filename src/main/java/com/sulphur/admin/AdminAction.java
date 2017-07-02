package com.sulphur.admin;

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

@Controller
@RequestMapping("/admin")
public class AdminAction {

	@Autowired
	private AdminDao adminDao;

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public @ResponseBody String login(@RequestParam("id") String id,
			@RequestParam("password") String password, HttpServletRequest req) {
		Password res = adminDao.checkLogin(id, password);
		if (res != null) {
			HttpSession session = req.getSession();
			session.setMaxInactiveInterval(1200);
			session.setAttribute("admin_id", id);
			return "1";
		}
		return "0";
	}

	@RequestMapping(value = "/update_pwd.do", method = RequestMethod.POST)
	public @ResponseBody String updatePass(@RequestParam("password") String password, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		String user = new String();
		if(session != null){
			user = (String) session.getAttribute("admin_id");
			Integer res = adminDao.updatePass(user, password);
			return res.toString();
		}
		else return "must login!";
	}
	
	@RequestMapping(value="/update_info.do", method = RequestMethod.POST)
	public @ResponseBody String updateInfo(HttpServletRequest req){
		HttpSession session = req.getSession(false);
		String user = new String();
		if(session != null){
			user = (String) session.getAttribute("admin_id");
//			user = "sulphur";
			String adminName = req.getParameter("admin_name");
			String adminPhone = req.getParameter("admin_phone");
			Admin admin = new Admin(user, adminName, adminPhone);
			Integer res = adminDao.updateInfo(admin);
			return res.toString();
		}
		else return "must login!";
	}
	
	@RequestMapping(value="/get_info.do", method= RequestMethod.GET)
	public @ResponseBody Admin getInfo(HttpServletRequest req){
		HttpSession session = req.getSession(false);
		String user = new String();
		if(session != null){
			user = (String) session.getAttribute("admin_id");
//			user = "sulphur";
			Admin res = adminDao.getInfoByID(user);
			return res;
		}
		else return null;
	}
}
