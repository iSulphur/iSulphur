package com.sulphur.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping(value = "test")
public class Test {

	@RequestMapping(value = "login.do")
	public @ResponseBody String test(){
		return "win";
	}
}
