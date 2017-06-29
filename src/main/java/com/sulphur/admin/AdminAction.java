package com.sulphur.admin;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/admin")
public class AdminAction {
	@Resource
	private AdminDaoImp adminDao;

	@RequestMapping("/login")
	public String login(@RequestParam("username") String username,@RequestParam("password") String password, HttpServletRequest req) {
		Map result = adminDao.checkLogin(username, password);
		if (result != null) {
			String token = DigestUtils.md5DigestAsHex(req.getSession().getId().getBytes());
			req.getSession().setAttribute("OnlineUser", result);
			req.getSession().setAttribute("token", token);
			return "redirect:/admin/admin-base-info.html";
		}
		return "redirect:/admin/admin-base-info.html";
	}
}
