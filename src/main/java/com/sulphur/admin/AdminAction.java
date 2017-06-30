package com.sulphur.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/admin")
public class AdminAction {
	
	@Autowired
	private AdminService adminService;

	@RequestMapping(value="/login", method=RequestMethod.POST) 
	public int login(@RequestParam("username") String username,@RequestParam("password") String password, HttpServletRequest req) {
//		Integer result = adminService.checkLogin(username, password);
		AdminVO admin = adminService.checkLogin(username, password);
		
//		if (result != 0) {
//			String token = DigestUtils.md5DigestAsHex(req.getSession().getId().getBytes());
//			req.getSession().setAttribute("OnlineUser", result);
//			req.getSession().setAttribute("token", token);
//			return "redirect:/admin/admin-base-info.html";
		if(admin != null){
			return 1;
		}
		return 0;
	}
}
