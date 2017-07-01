package com.sulphur.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/admin")
public class AdminAction {
	
	@Autowired
	private AdminDao adminDao;

	@RequestMapping(value="/login.do", method=RequestMethod.POST) 
	public @ResponseBody String login(@RequestParam("username") String username,@RequestParam("password") String password, HttpServletRequest req) {
//		Integer result = adminService.checkLogin(username, password);
		Admin admin = adminDao.checkLogin(username, password);
		
		if (admin != null) {
			String token = DigestUtils.md5DigestAsHex(req.getSession().getId().getBytes());
			req.getSession().setAttribute("User", admin);
			return "1";
		}
		return "0";

	}
}
