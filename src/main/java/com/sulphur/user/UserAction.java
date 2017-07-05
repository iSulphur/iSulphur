package com.sulphur.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sulphur.admin.Message;
import com.sulphur.admin.Password;

@Controller
@RequestMapping("/user")
public class UserAction {

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public @ResponseBody Message login(@RequestParam("id") String id, @RequestParam("password") String password,
			HttpServletRequest req) {
		Password res = userDao.checkLogin(id, password);
		Message message;
		if (res != null) {
			HttpSession session = req.getSession();
			session.setMaxInactiveInterval(1200);
			session.setAttribute("user_id", id);
			message = new Message("1");
		} else {
			message = new Message("0");
		}
		return message;
	}

	@RequestMapping(value = "/update_team.do", method = RequestMethod.POST)
	public @ResponseBody Message updateTeam(@RequestParam("team_name") String team_name,
			@RequestParam("project") String project, @RequestParam("team_leader") String team_leader,
			@RequestParam("leader_phone") String leader_phone, @RequestParam("leader_email") String leader_email,
			HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		String user = new String();
		Message message;
		//login status check
		if (session != null) {
			user = (String) session.getAttribute("user_id");
			Integer res = userDao.updateTeam(team_name, project, team_leader, leader_phone, leader_email, user);
			message = new Message(res.toString());
		} else {
			message = new Message(Message.ERROR, "ERROR", "Not Login!");
		}
		return message;
	}

	@RequestMapping(value = "/update_password.do", method = RequestMethod.POST)
	public @ResponseBody Message updatePass(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		String user = new String();
		Message msg;
		// login status check
		if (session != null) {

			// input password is the same as current password and new password
			// is the same as confirm password
			if (req.getParameter("currentPassword").equals(session.getAttribute("user_password"))
					&& req.getParameter("newPassword").equals(req.getParameter("confirmPassword"))) {
				user = (String) session.getAttribute("user_id");
				Integer result = userDao.updatePassword(user, req.getParameter("password"));
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

	@RequestMapping(value = "/agenda_report.do")
	public @ResponseBody Message agenda(HttpServletRequest req) {
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

	@RequestMapping(value = "/edit.do")
	public @ResponseBody Message edit(HttpServletRequest req) {
		//login status check
		Message msg;
		if (req.getSession() != null) {
			Report report = new Report();
			report.setReport_id(req.getParameter("report_id"));
			report.setUpload_date(req.getParameter("upload_date"));
			report.setTeam_id((String) req.getSession().getAttribute("user_id"));
			report.setProgress(req.getParameter("progress"));
			report.setHarvest(req.getParameter("harvest"));
			report.setNext_aim(req.getParameter("next_aim"));
			Integer result = userDao.insertReport(report);
			msg = new Message(result.toString());
		} else {
			msg = new Message(Message.ERROR, "ERROR", "Not login!");
		}
		return msg;
	}
}
